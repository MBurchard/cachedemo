package de.mbur.cachedemo.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Nonnull;

import de.mbur.cachedemo.domain.User;
import org.slf4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class UserService implements ApplicationContextAware, InitializingBean {
	public static final String CACHE_USERS = "users";
	private static final Logger LOG = getLogger(UserService.class);

	private final Map<String, User> users = new HashMap<>();
	private ApplicationContext ctx;
	private UserService self;

	@Override
	public void afterPropertiesSet() {
		self = ctx.getBean(UserService.class);
	}

	@Async
	public void asyncMethod() {
		LOG.debug("Do something async");
		try {
			Thread.sleep(10000);
		} catch (final InterruptedException e) {
			LOG.error("", e);
		}
		LOG.debug("Async Method finished...");
	}

	@CacheEvict(value = CACHE_USERS, key = "#user.customerID")
	public User create(@Nonnull final User user) {
		user.setId(UUID.randomUUID().toString());
		users.put(user.getId(), user);
		LOG.debug("User created: {}", user);
		return user;
	}

	@Cacheable(value = CACHE_USERS, key = "#cid")
	public List<User> getUsersByCustomer(@Nonnull final String cid) {
		final List<User> result = new ArrayList<>();
		users.forEach((key, value) -> {
			if (value.getCustomerID().equals(cid)) {
				result.add(value);
			}
		});
		LOG.debug("Users for Customer ID {}: {}", cid, result.size());
		return result;
	}

	@Override
	public void setApplicationContext(@Nonnull final ApplicationContext ctx)
			throws BeansException {
		this.ctx = ctx;
	}
}
