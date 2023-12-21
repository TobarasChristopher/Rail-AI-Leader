import tkinter as tk
import threading
import GUI


def run_gui():
    gui_window = tk.Tk()
    gui_window.title("GUI Window")
    GUI()
    gui_window.mainloop()



# Create threads for GUI and Proto
gui_thread = threading.Thread(target=run_gui)


# Start both threads
gui_thread.start()


# Wait for both threads to finish (optional)
gui_thread.join()

