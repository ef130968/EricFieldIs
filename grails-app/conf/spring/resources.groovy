import me.ericfieldis.framework.plugin.bootstrap.database.DataGenesis

// Place your Spring DSL code here
beans = {
    dataGenesis(DataGenesis) {
        creators = []
//        creators = [ref("profileDataCreator"), ref("personDataCreator")]
    }
}
