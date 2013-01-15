package postmeta

import groovy.swing.SwingBuilder
import javax.swing.*

class MetaPostWindow {
	
	def filePath
	def swingBuilder
	def swingWindow
	def editWindow
	
	MetaPostWindow(def x, def y, def file)
	{
		filePath = file
		swingBuilder = new SwingBuilder()
		swingWindow = swingBuilder.frame(title: "PostMeta", size:[x, y],
			show:true){
			def BL = borderLayout()
			menuBar(){
				menu(text: "File", mnemonic: 'F'){
					menuItem(text: "Load", mnemonic: 'L',
						actionPerformed: { loadFile() })
					menuItem(text: "Quit", mnemonic: 'Q',
						actionPerformed: { dispose() })
				}
				menu(text: "View", mnemonic: 'V') {
					menuItem(text: "View EPS output", mnemonic: 'E',,
						actionPerformed: {seeEPS()})
				}
			}
			
			scrollPane(constraints:BL.EAST) {
				editWindow = textArea()
			}
			
			
		}
		
		
		
	}
	
	def seeEPS()
	{
		
	}
	
	def loadFile()
	{
		
	}

}
