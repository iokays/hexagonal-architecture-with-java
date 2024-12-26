package com.iokays.common.core.service;

/**
 * 应用服务
 * 管理事务，安全，锁， 通用日志等非业务性行为
 * 并负责与第三方服务进行集成
 * 外部服务交互对业务流程的影响程度决定了它应该放在哪里。如果外部服务交互只是在应用层进行技术协调而不改变核心业务逻辑，那么它应该放在应用服务层。
 */
public interface ApplicationService {
}
