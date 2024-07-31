package elephant

open class ElephantExtension {
    /** The target version for 1D6 */
    var version: String = "newest-release"
    /** If Basemod should be used */
    var basemod: Boolean = true
    /** If 2DScript should be used */
    var script: Boolean = false
    /** Adds mods to the project from the 1D6 API */
    var addons: Map<String, String> = mutableMapOf()
}