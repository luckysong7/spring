package com.test1;

public class LoginAuthenticatorImpl extends Exception implements Authenticator {

	private static final long serialVersionUID = 1L;

	@Override
	public void authen(String userId, String userPwd) throws UserException {
		if (!userId.equals("suzi") && !userPwd.equals("a123")) {
			throw new UserException("인증 실패 !!!" + userId);
		}
	}

}