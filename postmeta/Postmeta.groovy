package postmeta

class MetaPostEditor{
	def metaPostFilePath
	
	def edit()
	{
		return 0
	}
	
	
}

def metaCli = new CliBuilder
	(usage: 'metaedit [options] <file>')
	
metaCli.u(longOpt: 'usage', 'show this information')
metaCli.f(longOpt:'file',
	'MetaPost file to edit')
metaCli.header = "Edit and process MetaPost files, copyright Adrian McMenamin, 2013, licensed under GPL v3"
	
def metaParse = metaCli.parse(args)

if (metaParse.u)
	metaCli.usage
else {
	def metaEditor
	
	if (args.size() == 0)
		metaEditor = new MetaPostEditor()
	else {
		def fileToEdit
		if (metaCli.f)
			metaEditor = new MetaPostEditor(metaPostFilePath:metaCli.f)
		else
			metaEditor = new MetaPostEditor(metaPostFilePath:args[args.size() - 1])
	}
	metaEditor.edit()	
}	