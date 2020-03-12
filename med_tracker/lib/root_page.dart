void loginCallback() {
  widget.auth.getCurrentUser().then((user) {
    setState(() {
      _userId = user.uid.toString();
    });
  });
  setState(() {
    authStatus = AuthStatus.LOGGED_IN;
  });
}

void logoutCallback() {
  setState(() {
    authStatus = AuthStatus.NOT_LOGGED_IN;
    _userId = "";
  });
}
@override
Widget build(BuildContext context) {
  switch (authStatus) {
    case AuthStatus.NOT_DETERMINED:
      return buildWaitingScreen();
      break;
    case AuthStatus.NOT_LOGGED_IN:
      return new LoginSignupPage(
        auth: widget.auth,
        loginCallback: loginCallback,
      );
      break;
    case AuthStatus.LOGGED_IN:
      if (_userId.length > 0 && _userId != null) {
        return new HomePage(
          userId: _userId,
          auth: widget.auth,
          logoutCallback: logoutCallback,
        );
      } else
        return buildWaitingScreen();
      break;
    default:
      return buildWaitingScreen();
  }
}