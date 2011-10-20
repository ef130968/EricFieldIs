import me.ericfieldis.framework.plugin.bootstrap.database.DataGenesis

// Place your Spring DSL code here
beans = {
    xmlns context: "http://www.springframework.org/schema/context"
    xmlns jee: "http://www.springframework.org/schema/jee"

    context.'component-scan'('base-package': 'me.ericfieldis')

    dataGenesis(DataGenesis) {
        creators = [ref("entityDataCreator")]
    }
}
