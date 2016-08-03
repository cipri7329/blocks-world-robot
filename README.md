# blocks-world-robot
a robot manipulating containers in a warehouse

### english version

* A robot manipulates containers in a warehouse.

* Containers are stacked ontop of each other.
* Each container has a name.
* The robot can receive commands of type "move b1 b2" which means move b1 over b2.
* Containers can be moved only if there no other containers above. If there are, those stacked above have to be moved also.

* Another possible command can be "fill b 50" which means fill container b with a volume of 50l.
* The container's filling cap is above, therefore the container should have no other containers above in order to be filled.
* The warehouse's ground floor space is limited.

* The containers' stack height is limited.
* The robot can also receive commands of type undo/redo which refer to the initial commands received and not at the additional moves implied by those commands.

* The requirements: design the robot.

### romanian version

* Un robot manipuleaza containere intr-o magazie. Containerele sunt stivuite si au nume. 
* Robotul poate primi comenzi de tipul “move b1 b2” ceea ce inseamna sa mute b1 peste b2.
* Containerele pot fi mutate doar daca nu mai sunt alte containere deasupra, deci eventual ar trebui mutate si alte containere.
* O alta comanda posibila ar fi “fill b 50” adica sa umple containerul b cu un volum de 50l. 
* Dopul de umplere e deasupra, deci din nou trebuie eliberat containerul daca e cazul.
* Spatiul pe podea este limitat si stivele de containere au inaltime limitata.
* Robotul poate primi si comenzi undo si redo care se refera la comenzile initial primite si nu la mutarile implicate de acele comenzi.
* Cerinta este sa se realizeze robotul.
