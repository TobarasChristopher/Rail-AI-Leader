import tkinter as tk

def closeWindow(window):
    window.destroy()


def startFunc():
    error = tk.Tk()
    error.title("Error!")
    label = tk.Label(error, text="Error Found!")
    label.pack(padx=5) 
    confirmButton = tk.Button(error,text ="Confirm", width=7, height=3, bg="#f02c1a", fg="black", command=lambda: closeWindow(error))
    confirmButton.pack(anchor="s", padx=5)
    error.geometry("200x150")
    error.mainloop()


