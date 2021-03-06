package zkr

import picocli.CommandLine

class RestoreOptions {
    @CommandLine.Option(
            names = ["--dry-run", "-d"],
            description = ["Do not actually perform the actions (default: \${DEFAULT-VALUE})"],
            defaultValue = "false"
    )
    var dryRun: Boolean = false

    @CommandLine.Option(
            names = ["--compress", "-c"],
            description = ["Compressed input (default: \${DEFAULT-VALUE})"],
            defaultValue = "false"
    )
    var compress: Boolean = false

    @CommandLine.Option(
            names = ["--overwrite-existing", "-o"],
            description = ["Overwrite existing znodes (default: \${DEFAULT-VALUE})"],
            defaultValue = "false"
    )
    var overwrite: Boolean = false

    @CommandLine.Option(
            names = ["--info"],
            description = ["Print information about transaction log or backup then exit  (default: \${DEFAULT-VALUE})"],
            defaultValue = "false"
    )
    var info: Boolean = false

    override fun toString(): String {
        return """
overwrite=$overwrite, info=$info 
        """.trimIndent()
    }

} //-RestoreOptions