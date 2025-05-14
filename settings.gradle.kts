rootProject.name = "weather-service"

include(
    ":modules:domain:weather-domain",
    ":modules:application:sync-application",
    ":modules:application:inquire-application",
    ":modules:boot",
    ":modules:internal:common-utils"
)
