package com.iokays.authorization.core.application.service;

import com.iokays.authorization.core.domain.authorization.Authorization;
import com.iokays.authorization.core.domain.authorization.AuthorizationId;
import com.iokays.authorization.core.domain.authorization.AuthorizationInfo;
import com.iokays.authorization.core.domain.authorization.AuthorizationRepository;
import com.iokays.authorization.core.domain.authorization.command.SaveAuthorization;
import com.iokays.common.core.service.ApplicationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class AuthorizationApplicationService implements ApplicationService {

    private final AuthorizationRepository authorizationRepository;

    @CacheEvict(value = "AuthorizationInfoByAuthorizationId", key = "#result.id")
    public AuthorizationId save(final SaveAuthorization command) {
        final var authorizationId = command.authorizationId();

        authorizationRepository.findByAuthorizationId(authorizationId)
                .ifPresentOrElse(
                        entity -> entity.update(command),
                        () -> authorizationRepository.save(new Authorization(command))
                );

        return authorizationId;
    }

    @CacheEvict(value = "AuthorizationInfoByAuthorizationId", key = "#authorizationId.id")
    public void remove(final AuthorizationId authorizationId) {
        authorizationRepository.deleteByAuthorizationId(authorizationId);
    }

    @Cacheable(value = "AuthorizationInfoByAuthorizationId", key = "#authorizationId.id")
    public AuthorizationInfo findByAuthorizationId(final AuthorizationId authorizationId) {
        return authorizationRepository.findByAuthorizationId(authorizationId).map(Authorization::info).orElse(null);
    }

    /**
     * 这部分的缓存不好处理，原因在于缓存的清除。
     * <p></p>
     * 解决方案：两级缓存
     * 1. 根据token+type获取AuthorizationId. (不再动态维护删除操作， 设置为默认固定的过期时间) 详见：findAuthorizationIdByToken
     * 2. 根据AuthorizationId获取AuthorizationInfo. (在动态维护变更操作)
     * <p></p>
     * 难点在于，这部分的解决方案是否需要放在应用服务层。该层是否需要为这个技术细节负责。 查看： findByToken
     *
     * @param tokenValue
     * @param tokenType
     * @return
     */
    public AuthorizationInfo findByToken(final String tokenValue, final String tokenType) {
        final Optional<Authorization> entity = switch (tokenType) {
            case "code" -> authorizationRepository.findByAuthorizationCodeValue(tokenValue);
            case "state" -> authorizationRepository.findByState(tokenValue);
            case "access_token" -> authorizationRepository.findByAccessTokenValue(tokenValue);
            case "refresh_token" -> authorizationRepository.findByRefreshTokenValue(tokenValue);
            case "id_token" -> authorizationRepository.findByOidcIdTokenValue(tokenValue);
            case "user_code" -> authorizationRepository.findByUserCodeValue(tokenValue);
            case "device_code" -> authorizationRepository.findByDeviceCodeValue(tokenValue);
            default -> Optional.empty();
        };

        return entity.map(Authorization::info).orElse(null);
    }

    /**
     * @param tokenValue
     * @return
     */
    public AuthorizationInfo findByToken(final String tokenValue) {
        return Optional.of(tokenValue)
                .map(_this()::findAuthorizationIdByToken)
                .map(_this()::findByAuthorizationId)
                .orElse(null);
    }

    @Cacheable(value = "AuthorizationIdByTokenValue", key = "#tokenValue")
    protected AuthorizationId findAuthorizationIdByToken(final String tokenValue) {
        return authorizationRepository
                .findByStateOrAuthorizationCodeValueOrAccessTokenValueOrRefreshTokenValueOrOidcIdTokenValueOrUserCodeValueOrDeviceCodeValue(tokenValue)
                .orElse(null);
    }

    /**
     * 获取当前代理对象，使用Spring代理来调用内部方法，避免方法上的注解失效
     *
     * @return
     */
    private AuthorizationApplicationService _this() {
        return (AuthorizationApplicationService) AopContext.currentProxy();
    }

}
