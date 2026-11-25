package com.pouw.workload;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BuildInfoTest {
    @Test
    void namesTheCommitBranchAndBuildTimeOfTheImage() {
        Map<String, String> environment = Map.of(
                "GIT_COMMIT", "0123456789abcdef",
                "GIT_BRANCH", "feature/x",
                "BUILD_TIME", "");
        Map<String, String> info = BuildInfo.of(environment::get, "0.1.0");
        assertEquals("pouw-workload", info.get("service"));
        assertEquals("0.1.0", info.get("version"));
        assertEquals("0123456789abcdef", info.get("commit"));
        assertEquals("feature/x", info.get("branch"));
        // set but empty (a build without the build arg) is unknown as well
        assertEquals("unknown", info.get("build_time"));
    }

    @Test
    void withoutAnImageEverythingButTheServiceIsUnknown() {
        Map<String, String> info = BuildInfo.of(name -> null, null);
        assertEquals(Map.of("service", "pouw-workload", "version", "unknown", "commit", "unknown",
                "branch", "unknown", "build_time", "unknown"), info);
    }
}
