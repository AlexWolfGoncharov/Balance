package com.github.alexwolfgoncharov.balance.services;


import com.github.alexwolfgoncharov.balance.security.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

//	@Autowired
	private UserService userService = new UserServiceImpl();

	public UserDetails loadUserByUsername(String login)
			throws UsernameNotFoundException {
		// с помощью нашего сервиса UserService получаем User
		User user = userService.getUser(login);
		// указываем роли для этого пользователя
	
		return user;
//		Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
//		roles.add(new SimpleGrantedAuthority(UserRoleEnum.USER.name()));

		// на основании полученныйх даных формируем объект UserDetails
		// который позволит проверить введеный пользователем логин и пароль
		// и уже потом аутентифицировать пользователя
//		UserDetails userDetails = new org.springframework.security.core.userdetails.User(
//				user.getLogin(), user.getPassword(), roles);
//		UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), user.isEnabled(), user.isAccountNonExpired(),
//	         user.isCredentialsNonExpired(), user.isAccountNonLocked(),  user.getAuthorities());
//		
////		UserDetails userDetails = new User(user.getLogin(), user.getPassword(), user.isEnabled(), user.isAccountNonExpired(),
////		         user.isCredentialsNonExpired(), user.isAccountNonLocked(),  user.getAuthorities());
//		
//		return userDetails;
	}

}