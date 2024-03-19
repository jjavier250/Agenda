1. En el activity se usa un reciclerView llamando a la base de datos mostrando los datos de la agenda
este contempla un icono papelera de borrado lanzando un dialogo preguntando si deseas borrar
sobre el texto nos lleva a la pantalla de editar (punto 3)
Desde esta pantalla tenemos un botón flotante para añadir que abriráala pantalla de añadir (punto 2)
en la parte superior tiene un menu con el componente SearchView filtrando y actualizando el ReciclerView
Cuando se marque en favorito desde la pantalla de modificar (punto 3) (haciendo click sobre la linea de texto del ReciclerView) esa linea del ReciclerView se marcara en color

   
2.Pantalla de insercicón. Desde la pantalla principal activity desde el botón flotante nos abrira la pantalla con los campos a insertar.
dicha pantalla tiene validación de campos de nombre y telefono. Incorpora flecha de retroceso  o botón de ir hacia atras

3. Pantalla de editar/modificar realizar llamada compartir enviar correo y compartir mensaje y favorito
Desde esta pantalla se puede modificar los datos, pulsar al corazón cambianado de color a rojo para marcarlo como favorito y en la ventana principal (Activity lo marcará en color)



NOTA:
El proyecto incluye el fichero String con 1 idioma el uso de Binding en 2 pantallas, uso con FindViewById tambien el la activity, uso de SharedPreferences en la activity y en la de modificación
Base de datos con 6 campos, ReciclerView, dialogos de respuesta a acciones , 3 pantallas,pasar parametro extra a las otras pantallas,recuperarlo en las otras pantalla, uso de  AppBar con un SearcView y Compartir

