package postmeta

class MetaPostEditor{
	def metaPostFilePath
	def bigWindow
	def mpEditWindow
	def epsDisplayWindow
	def xWin
	def yWin
	
	def edit()
	{
		bigWindow = new MetaPostWindow(xWin, yWin, metaPostFilePath)
		return 0
	}
	
}

def metaCli = new CliBuilder
	(usage: 'metaedit [options] <file>')
	
metaCli.u(longOpt: 'usage', 'show this information')
metaCli.f(longOpt:'file',
	'MetaPost file to edit')
metaCli.w(longOpt:'width', 'Width of initial window')
metaCli.h(longOpt:'height', 'Height of initial window')
metaCli.header = 
	"Edit and process MetaPost files, copyright Adrian McMenamin, 2013,"
 " licensed under GPL v3"
	
def metaParse = metaCli.parse(args)

if (metaParse.u)
	metaCli.usage
else {
	def metaEditor
	def width = 640
	def height = 480
	if (metaParse.w)
		width = metaParse.w
	if (metaParse.h)
		height = metaParse.h
	if (args.size() == 0)
		metaEditor = new MetaPostEditor(xWin:width, yWin:height)
	else {
		def fileToEdit
		
		if (metaParse.f)
			metaEditor = new MetaPostEditor(xWin:width, yWin:height,
				 metaPostFilePath:metaParse.f)
		else
			metaEditor = new MetaPostEditor(xWin:width, yWin:height,
					metaPostFilePath:args[args.size() - 1])
	}
	metaEditor.edit()	
}	