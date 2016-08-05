# blocks-world-robot
a robot manipulating containers in a warehouse

### english version

* A robot manipulates containers in a warehouse.

* Containers are stacked on top of each other.
* Each container has a name.
* The robot can receive commands of type "move b1 b2" which means move b1 over b2.
* Containers can be moved only if there no other containers above. If there are, those stacked above have to be moved also.

* Another possible command can be "fill b 50" which means fill container b with a volume of 50l.
* The container's filling cap is above, therefore the container should have no other containers above in order to be filled.
* The warehouse's ground floor space is limited.

* The containers' stack height is limited.
* The robot can also receive commands of type undo/redo which refer to the initial commands received and not at the additional moves implied by those commands.

* The requirements: design the robot.

### questions
* What should happen if a container is already close to full and the robot has a command to fill with more than available?
* What happens with redo for a <fill> command? Is the robot capable of emptying a container?
* assume containers have unique names

### commands
* add <container-id> - adds the container to the first available stack
* add <container-id> <stack-id> - adds the container to the stack with the given id

* top <container-id> - shuffles the containers such that <container-id> is a the top of a stack
* top <container-1> <container-2> - shuffles the containers such that <container-1> and <container-2> are at the top of two different stacks

* move <container-1> <container-2> - moves <container-1> over <container-2>

* fill <container-id> <amount> - fills the <container-id> with the given <amount>

* undo - goes back to the previous given command. intermmediate steps are not considered commands
* redo - reexecutes the last given command. intermmediate steps are not considered commands

### commands file

#comment
*comment
add cont1
add cont2 2

### warehouse properties
stack.number=6 - the number of stacks in the warehouse. stacks are 0-based indexed
stack.height=4 - the height of each stack in the warehouse. height is 0-based indexed


### romanian version

* Un robot manipuleaza containere intr-o magazie. Containerele sunt stivuite si au nume. 
* Robotul poate primi comenzi de tipul “move b1 b2” ceea ce inseamna sa mute b1 peste b2.
* Containerele pot fi mutate doar daca nu mai sunt alte containere deasupra, deci eventual ar trebui mutate si alte containere.
* O alta comanda posibila ar fi “fill b 50” adica sa umple containerul b cu un volum de 50l. 
* Dopul de umplere e deasupra, deci din nou trebuie eliberat containerul daca e cazul.
* Spatiul pe podea este limitat si stivele de containere au inaltime limitata.
* Robotul poate primi si comenzi undo si redo care se refera la comenzile initial primite si nu la mutarile implicate de acele comenzi.
* Cerinta este sa se realizeze robotul.
