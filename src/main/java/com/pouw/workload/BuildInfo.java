package com.pouw.workload;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.UnaryOperator;

/**
 * What runs here, answered at {@code GET /manage/info}. The image has GIT_COMMIT, GIT_BRANCH and
 * BUILD_TIME from its build args (pouw-deploy {@code make images}, docker.yml), the version comes
 * from the manifest of the jar; outside an image they are "unknown".
 */
final class BuildInfo {
    private static final String UNKNOWN = "unknown";

    private BuildInfo() {
    }

    static Map<String, String> fromEnvironment() {
        return of(System::getenv, BuildInfo.class.getPackage().getImplementationVersion());
    }

    static Map<String, String> of(UnaryOperator<String> environment, String version) {
        Map<String, String> info = new LinkedHashMap<>();
        info.put("service", "pouw-workload");
        info.put("version", orUnknown(version));
        info.put("commit", orUnknown(environment.apply("GIT_COMMIT")));
        info.put("branch", orUnknown(environment.apply("GIT_BRANCH")));
        info.put("build_time", orUnknown(environment.apply("BUILD_TIME")));
        return info;
    }

    private static String orUnknown(String value) {
        return value == null || value.isEmpty() ? UNKNOWN : value;
    }
}
