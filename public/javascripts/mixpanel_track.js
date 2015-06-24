var url = window.location.pathname
if(url === "/mobile_developer"){
	mixpanel.track('pageView-app');
} else if (url === "/payment_provider"){
	mixpanel.track('pageView-pp');
} else {
	mixpanel.track('pageView');
}
