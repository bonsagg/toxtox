package toxtox.app.server.services.impl;

import javax.inject.Inject;

import junit.framework.Assert;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import toxtox.app.client.shared.entities.AbstractEntity;
import toxtox.app.client.shared.entities.User;
import toxtox.app.server.producer.LoggerProducer;
import toxtox.app.server.services.api.UserService;

@RunWith(Arquillian.class)
public class DBUserServiceTest {

	@Inject
	private UserService dbUserService;

	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap
				.create(JavaArchive.class)
				.addClasses(AbstractEntity.class, User.class,
						LoggerProducer.class, UserService.class,
						DBUserService.class)
				.addPackages(true, "org.apache.shiro")
				.addClass(org.aspectj.lang.NoAspectBoundException.class)
				.addClass(org.aspectj.lang.JoinPoint.class)
				.addClass(org.aspectj.lang.Signature.class)
				.addClass(org.aspectj.lang.reflect.SourceLocation.class)
//				.addPackages(true, "org.aspectj")
//				.addClass(org.apache.shiro.SecurityUtils.class)
//				.addClass(org.apache.shiro.ShiroException.class)
//				.addClass(org.apache.shiro.authc.AuthenticationToken.class)
//				.addClass(org.apache.shiro.authc.CredentialsException.class)
//				.addClass(org.apache.shiro.authc.LockedAccountException.class)
//				.addClass(org.apache.shiro.authc.UnknownAccountException.class)
//				.addClass(org.apache.shiro.authc.AuthenticationException.class)
//				.addClass(org.apache.shiro.authc.IncorrectCredentialsException.class)
//				.addClass(org.apache.shiro.authc.UsernamePasswordToken.class)
//				.addClass(org.apache.shiro.authc.HostAuthenticationToken.class)
//				.addClass(org.apache.shiro.authc.AccountException.class)
//				.addClass(org.apache.shiro.authc.DisabledAccountException.class)
				.addAsResource("test-persistence.xml",
						"META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	private User createUser(String firstName, String lastName, String userName,
			String password) {
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setUserName(userName);
		user.setPassword(password);
		return user;
	}
	
	@Test
	public void testSaveAndFindUserSuccess() {
		User user = createUser("test", "test", "test", "test");
		dbUserService.saveUser(user);
		User userFound = dbUserService.findUserByUserName("test");

		Assert.assertNotNull(userFound);
		Assert.assertEquals("test", userFound.getFirstName());
	}

	@Test
	public void testSaveExisitingUser() {
		User user = createUser("hans", "hans", "hans", "hans123");
		dbUserService.saveUser(user);
		User userFound = dbUserService.findUserByUserName("hans");

		userFound.setFirstName("newHans");
		dbUserService.saveUser(userFound);

		userFound = dbUserService.findUserByUserName("hans");

		Assert.assertEquals("newHans", userFound.getFirstName());
	}

	@Test
	public void testFindUserFail() {
		User user = dbUserService.findUserByUserName("notThere");

		Assert.assertNull(user);
	}

	@Test
	public void testDeleteUser() {
		User toDel = createUser("delete", "delete", "delete", "delete");
		dbUserService.saveUser(toDel);
		
		toDel = dbUserService.findUserByUserName("delete");
		
		dbUserService.deleteUser(toDel);
		
		Assert.assertNull(dbUserService.findUserByUserName("delete"));
	}

	@Test
	public void testExistsUsername() {
		User user = createUser("testExist", "testExist", "testExist",
				"testExist");
		dbUserService.saveUser(user);

		boolean exist = dbUserService.existsUsername("testExist");
		Assert.assertTrue(exist);
		
		exist = dbUserService.existsUsername("freeUsername");
		Assert.assertFalse(exist);
	}

}
