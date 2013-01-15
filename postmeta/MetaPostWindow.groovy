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
					menuItem(text: "View EPS output", mnemonic: 'E',
						actionPerformed: {seeEPS()})
				}
				menu(text: 'About', mnemonic: 'A') {
					menuItem(text: "Licence terms", mnemonic: 'i',
						actionPerformed: {displayGPL()})
				}
			}
			
			scrollPane(constraints:BL.LINE_START) {
				editWindow = textArea(visible:true, editable:true, text:"some oul text")
			}
			
			
		}
		
		
		
	}
	
	def seeEPS()
	{
		
	}
	
	def loadFile()
	{
		def loadDialog = swingBuilder.fileChooser(
			dialogTitle: "Choose a MetaPost file to open"
		)
		loadDialog.showOpenDialog()
		filePath = loadDialog.getSelectedFile()
		changeFileName()
	}
	
	def changeFileName()
	{
		
	}
	
	def displayGPL()
	{
		def name = "Postmeta"
		def version = "0.1"
		def copyright = "copyright (c) Adrian McMenamin, 2013"
		def message =
		name +  " v" + version + "\n" + "\n" + copyright +
		"\n\nThis program is free software, You can redistribute" +
		"\nit and/or modify it under the terms of the GNU" +
		"\nGeneral Public License (GPL) as published by the " +
		"\nFree Software Foundation; either version 3 of the" +
		"\nlicence, or (at your option) any later version.\n";
		
		def gPLDialog = swingBuilder.frame(title: "Licence terms",
			size: [320, 200]) {
			def BL = borderLayout()
			scrollPane(constraints: BL.CENTER){
				textArea(text: message)}
		}
		gPLDialog.pack()
		gPLDialog.show()
	}

}
