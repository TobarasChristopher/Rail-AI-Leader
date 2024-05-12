import tkinter as tk

def closeWindow(window):
    window.destroy()


def startFunc():
    error = tk.Tk()
    error.title("Error!")
    label = tk.Label(error, text="Error Found!")
    label.pack(pady=(20, 5)) 
    confirmButton = tk.Button(error,text ="Confirm", width=7, height=3, fg="black", command=lambda: closeWindow(error))
    confirmButton.pack(pady=(5, 20))
    error.geometry("200x120")
    error.mainloop()


def stopFunc():
    error = tk.Tk()
    error.title("")
    label = tk.Label(error, text="Simulation already stopped!")
    label.pack(pady=(20, 5)) 
    confirmButton = tk.Button(error,text ="Confirm", width=7, height=3, fg="black", command=lambda: closeWindow(error))
    confirmButton.pack(pady=(5, 20))
    error.geometry("200x120")
    error.mainloop()


