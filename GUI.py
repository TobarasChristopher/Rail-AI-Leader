import tkinter as tk
from GUIFunc import startFunc
window = tk.Tk()

window.title("Rail AI Leader")

#Frames

label = tk.Label(window, text="Version 0.1 Prototype")
label.pack(anchor="nw", padx=5)

buttonFrame = tk.Frame(window)
buttonFrame.pack(anchor="nw", padx=5)

#Buttons

startButton = tk.Button(buttonFrame, text ="Start", command=startFunc , width=15, height=7, bg="#3bbd24", fg="black")
startButton.grid(row=0,column=1, padx=5)

stopButton = tk.Button(buttonFrame, text ="Stop", width=15, height=7, bg="#f02c1a", fg="black")
stopButton.grid(row=0,column=2, padx=5)

feedButton = tk.Button(buttonFrame, text ="Feed Schedule", width=15, height=7, bg="#3882c7", fg="black")
feedButton.grid(row=0,column=3, padx=5)

logsButton = tk.Button(buttonFrame, text ="View Logs", width=15, height=7, bg="#dbdb16", fg="black")
logsButton.grid(row=0,column=4, padx=5)

debugButton = tk.Button(buttonFrame, text ="Debug", width=15, height=7, bg="#8137a1", fg="black")
debugButton.grid(row=0,column=5, padx=5)

manualButton = tk.Button(buttonFrame, text ="Manual", width=15, height=7, bg="#ed951a", fg="black")
manualButton.grid(row=0,column=6, padx=5)

#Window Attributes

window.geometry("775x160")
window.resizable(False, False)

window.mainloop()
