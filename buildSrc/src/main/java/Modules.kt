object Modules {

    const val APP = ":app"

    private const val LIBS_DIR = ":libraries"
    private const val FEATURES_DIR = ":features"

    object AndroidLibrary {
        const val CORE = "${LIBS_DIR}:core"
    }

    /**
     * Dynamic Feature Modules
     */
    object DynamicFeature {
        const val NFS_LOGIN = "${FEATURES_DIR}:nfslogin"
    }
}