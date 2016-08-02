# blocks-world-robot
a robot manipulating containers in a wharehouse




### romanian version

Un robot manipuleaza containere intr-o magazie. Containerele sunt stivuite si au nume. 
Robotul poate primi comenzi de tipul “move b1 b2” ceea ce inseamna sa mute b1 peste b2.
Containerele pot fi mutate doar daca nu mai sunt alte containere deasupra, deci eventual ar trebui mutate si alte containere.
O alta comanda posibila ar fi “fill b 50” adica sa umple containerul b cu un volum de 50l. Dopul de umplere e deasupra, deci din nou trebuie eliberat containerul daca e cazul.
Spatiul pe podea este limitat si stivele de containere au inaltime limitata.
Robotul poate primi si comenzi undo si redo care se refera la comenzile initial primite si nu la mutarile implicate de acele comenzi.
Cerinta este sa se realizeze robotul.
