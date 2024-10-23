package com.generator.writer.frontend.angular.types;

public enum AngularDependencies {
//    JWT("@auth0/angular-jwt"),
    PRIME_NG("primeng"),
    PRIME_ICONS("primeicons"),
    FLAG_ICONS("flag-icons"),
    NGX_TRANSLATE_CORE("@ngx-translate/core"),
    NGX_TRANSLATE_HTTP_LOADER("@ngx-translate/http-loader"),
//    CHART_JS("chart.js"),
//    NGX_CHARTS("@swimlane/ngx-charts"),
    MOMENT("moment"),
    DATE_FNS_TZ("date-fns-tz"),
    NGX_COOKIE_SERVICE("ngx-cookie-service"),
    PRIMEFLEX("primeflex");

    private final String packageName;

    AngularDependencies(String packageName) {
        this.packageName = packageName;
    }

    public String getPackageName() {
        return packageName;
    }
}