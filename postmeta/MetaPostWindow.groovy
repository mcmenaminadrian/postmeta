package postmeta

import groovy.swing.SwingBuilder
import javax.swing.*

class MetaPostWindow {
	
	def filePath
	def swingBuilder
	def swingWindow
	def editWindow
	def fileOpen
	def fileDirty
	def fileObject
	def fileChan
	
	def subscribersFileOpen = []
	def subscribersFileName = []
	
	MetaPostWindow(def x, def y, def fileP)
	{
		filePath = fileP
		fileOpen = false
		fileDirty = false
		swingBuilder = new SwingBuilder()
		swingWindow = swingBuilder.frame(title: "PostMeta", size:[x, y],
			show:true, defaultCloseOperation: JFrame.EXIT_ON_CLOSE){
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
		if (displayFile())
			fileOpenOrClose()	
	}
	
	def seeEPS()
	{
		
	}
	
	def loadFile()
	{
		if (fileOpen && fileDirty)
			if (closeDirtyFile() == false)
				return
		def loadDialog = swingBuilder.fileChooser(
			dialogTitle: "Choose a MetaPost file to open"
		)
		loadDialog.showOpenDialog()
		filePath = loadDialog.getSelectedFile()
		changeFileName()
	}
	
	def closeDirtyFile()
	{
		fileOpen = false
		fileDirty = false
		fileOpenOrClose()
		return true
	}
	
	def changeFileName()
	{
		changeFilePath()
	}
	
	def displayFile()
	{
		filePath = filePath.trim()
		if (filePath.size() == 0)
			return false
		try {
			fileObject = new RandomAccessFile(filePath, "rw")
			fileChan = randomFile.getChannel()
			fileChan.position(0)
		}
		catch(e)
		{
			println "Unable to open $filePath, exception $e"
		}
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
		
		def gPLDialog
		
		gPLDialog = swingBuilder.frame(title: "Licence terms",
			size: [320, 200]) {
			def BL = borderLayout()
			scrollPane(constraints: BL.CENTER){
				textArea(text: message, editable:false)}
			button(constraints: BL.SOUTH, defaultButton:true,
				action: action(name:'OK', closure:{gPLDialog.dispose()}))
		}
		gPLDialog.pack()
		gPLDialog.show()
	}
	
	//code to allow subscribers to be updated on file status
	void subscribeFileOpen(def subscriber)
	{
		susbcribersFileOpen -= subscriber
		subscribersFileOpen << subscriber
	}
	
	void subscribeFilePath(def subscriber)
	{
		susbcribersFilePath -= subscriber
		subscribersFilePath << subscriber
	}

	void unsubscribeFileOpen(def subscriber)
	{
		subscribersFileOpen -= subscriber
	}
	
	void unsubscribeFilePath(def subscriber)
	{
		subscribersFilePath -= subscriber
	}
	
	void changeFilePath()
	{
		subscribersFilePath.each{it.updateFilePath(filePath)}
	}
	
	void fileOpenOrClose()
	{
		susbcribersFileOpen.each{it.updateFileOpenOrClose(fileOpen)}
	}
}
