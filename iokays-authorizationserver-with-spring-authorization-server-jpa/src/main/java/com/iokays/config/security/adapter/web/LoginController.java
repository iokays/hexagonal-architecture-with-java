/*
 * Copyright 2020-2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.iokays.config.security.adapter.web;

import com.iokays.config.security.adapter.persistence.MyClientRegistrationRepositoryAdapter;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ClassUtils;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

/**
 * @author Steve Riesenberg
 * @since 1.1
 */
@Controller
@AllArgsConstructor
public class LoginController {

	private final SecurityFilterChain securityFilterChain;

	private final MyClientRegistrationRepositoryAdapter clientRegistrationRepositoryAdapter;

	/**
	 * 		return HtmlTemplates.fromTemplate(LOGIN_PAGE_TEMPLATE)
	 * 			.withRawHtml("contextPath", contextPath)
	 * 			.withRawHtml("javaScript", renderJavaScript(request, contextPath))
	 * 			.withRawHtml("formLogin", renderFormLogin(request, loginError, logoutSuccess, contextPath, errorMsg))
	 * 			.withRawHtml("oneTimeTokenLogin",
	 * 					renderOneTimeTokenLogin(request, loginError, logoutSuccess, contextPath, errorMsg))
	 * 			.withRawHtml("oauth2Login", renderOAuth2Login(loginError, logoutSuccess, errorMsg, contextPath))
	 * 			.withRawHtml("saml2Login", renderSaml2Login(loginError, logoutSuccess, errorMsg, contextPath))
	 * 			.withRawHtml("passkeyLogin", renderPasskeyLogin())
	 * 			.render();
	 * @param model
	 * @return
	 */
	@GetMapping(DefaultLoginPageGeneratingFilter.DEFAULT_LOGIN_PAGE_URL)
	public String login(Model model) {
		this.renderFormLogin(model);
		this.renderOAuth2Login(model);
		return "login";
	}

	private void renderFormLogin(final Model model) {
		securityFilterChain.getFilters().stream()
				.filter(filter -> ClassUtils.isAssignable(UsernamePasswordAuthenticationFilter.class, filter.getClass()))
				.map(UsernamePasswordAuthenticationFilter.class::cast)
				.findFirst()
				.ifPresent(filter -> {
					model.addAttribute("formLoginEnabled", true);
					model.addAttribute("loginUrl", DefaultLoginPageGeneratingFilter.DEFAULT_LOGIN_PAGE_URL);
					model.addAttribute("usernameParameter", filter.getUsernameParameter());
					model.addAttribute("passwordParameter", filter.getPasswordParameter());
				});
	}

	private void renderOAuth2Login(final Model model) {
		final Map<String, String> loginUrlToClientName = clientRegistrationRepositoryAdapter.loginUrlToClientName();
		if (loginUrlToClientName.isEmpty())  {
			return;
		}

		model.addAttribute("oauth2LoginEnabled", true);
		loginUrlToClientName.forEach((loginUrl, clientName) -> {

		});
	}


}
