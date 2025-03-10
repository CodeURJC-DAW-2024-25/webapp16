## *webapp16 - Teatro DAW*

Gestión de venta de entradas de clubbing, conciertos y eventos 

---

## 📌 *Preparación 1: Definición de la Aplicación*

### 🎭 *Nombre de la Aplicación*

*TEATRO DAW* - Gestión de eventos y venta de entradas para el teatro.

---
| ID  | Nombre          | Email                           | Github         | 
|-----|-----------------|---------------------------------|----------------| 
| 1   | Alberto Acebes  | a.acebes.2021@alumnos.urjc.es   | alberr22       |     
| 2   | Jon Mazcuñan    | j.mazcunan.2022@alumnos.urjc.es | jonmazh        |  
| 3   | Alejandro Rico  | a.rico.2022@alumnos.urjc.es     | ALEJANDR0-RIC0 | 
| 3   | Gaizka Arranbari| g.aranbarri.2022@alumnos.urjc.es| GaizkaURJC     | 

---
## *Entidades*

### *1. Evento*
Representa los diferentes tipos de eventos que se pueden realizar en el teatro, como clubbing, conciertos y eventos corporativos.
- *Atributos*: 
  - ID
  - Nombre
  - Tipo [Clubbing, Concierto, Evento Corporativo]
  - Fecha
  - Hora
  - Descripción
  - Precio de entrada
  - Capacidad máxima
  - Entradas por vender

### *2. Entrada*
Representa las entradas vendidas para cada evento.
- *Atributos*: 
  - ID
  - EventoID
  - Precio
  - Fecha de venta
  - Estado [Disponible, Vendida]

### *3. Usuario*
Representa a los usuarios registrados en el sistema.
- *Atributos*:
  - ID
  - Nombre
  - Email
  - Contraseña (Hash)
  - Teléfono
  - TipoUsuario [Administrador, Registrado]
  - Fecha de Registro

### *4. Reserva*
Representa las reservas realizadas para los eventos corporativos.
- *Atributos*:
  - ID
  - ClienteID
  - EventoID
  - Estado [Confirmado (Pagado), Pendiente, Cancelado]
  - Fecha de Inicio
  - Fecha de Fin

### *5. Comentarios*
Representa los comemtarios realizados por los usuarios en los eventos .
- *Atributos*:
  - Evento_ID
  - ClienteID
  - Comentario
  - Fecha_Comentario

## Diagrama entidad relación (provisional)
![erDAW drawio](https://github.com/user-attachments/assets/7ccc0ac7-46f3-494e-b808-3659a4460e7c)


---

## *Tipos de Usuario*

### *1. Usuario Anónimo/No Registrado*
- Solo puede navegar por la web para:
  - Consultar las fiestas disponibles, precios y lo que incluyen.
  - Ver los conciertos anunciados.
  - Consultar fotos de eventos realizados.
- *Acción posible*: Registro para transformarse en un usuario registrado.

### *2. Usuario Registrado*
- Debe autenticarse para:
  - Comprar entradas de cualquier tipo.
  - Dejar comentarios en eventos.
  - Consultar el historial de compras.
  - Solicitar presupuestos para eventos corporativos.

### *3. Usuario Administrador*
- Uso exclusivo para el jefe de sala.
- Puede realizar las siguientes acciones:
  - Editar información de la web.
  - Cambiar precios de entradas.
  - Añadir/eliminar eventos (clubbing, conciertos).
  - Confirmar o rechazar eventos corporativos.
  - Gestionar usuarios registrados.

---

## *Permisos de los Usuarios*

### *1. Usuario No Registrado*
- Solo tiene permisos para navegar y registrarse.

### *2. Usuario Registrado*
- Permisos:
  - Acceder y editar los datos de su cuenta.
  - Consultar historial de pedidos.
  - Borrar o editar sus comentarios en eventos.

### *3. Usuario Administrador*
- Permisos:
  - Crear, editar y eliminar eventos.
  - Confirmar o rechazar eventos corporativos.
  - Gestionar cuentas de usuarios registrados.
  - Acceso completo a las funciones de un usuario registrado.

---

## *Imágenes*
Nuestra web permitirá:
- Subida de imágenes para avatares de usuarios y artistas en conciertos.
- Subida de fotos de eventos realizados en el teatro (solo por administradores).

---

## *Gráficos*
- Gráfico de ocupación de sesiones de noche:
  - Porcentaje de ocupación.
  - Sexo de los clientes.
  - Media de edad.

---

## *Tecnología Complementaria*
- *Envío de correos electrónicos*: Confirmación de creación de cuentas.
- *Generador de PDFs*: Presupuestos para eventos corporativos.
- *Mapa interactivo*: Ubicación del teatro.

---

## *Algoritmo o Consulta Avanzada*

Nuestra aplicación contará con un sistema de recomendación de eventos para usuarios registrados.

### *Algoritmo de Recomendación*
- *Tipo*: Filtrado basado en contenido.
- *Lógica*:
  - Si el usuario ha asistido a eventos de un género musical específico, se le recomendarán eventos similares basados en descripción o tipo de evento.
- *Implementación*:
  - Usar la base de datos para almacenar las preferencias de los usuarios.
  - Consultas avanzadas para encontrar eventos similares.

--------
### *Capturas de las pantallas*
### *Capturas de pantalla*

![image](https://github.com/user-attachments/assets/4ed5001b-4c6a-4219-9af5-41d6c3fb8f88)

Comenzamos la navegacion por nuestra pagina web en la pantalla principal, aqui he os optado por la simplicidad con un fondo de la sala de fiesta, arriba encontramos un header con un background-color transparente para darle un toque de elegancia, y donde encontramos una navegacion con la libreria aos, y un login donde el usuario, en caso de no ser anonimo pueda registrarse o iniciar sesion.

![image](https://github.com/user-attachments/assets/e168634e-98a3-467f-a0d8-2bd196342f5b)

Una vez le das al icono de login, se desplegara un modal, en el que rellenando los campos de forma correcta, cada usuario podra acceder a su cuenta y poder realizar diversas funciones.

![image](https://github.com/user-attachments/assets/a04a34ee-e805-497a-af52-17b4bcf3eb96)

en caso de no tener cuenta, nuestra aplicacion te da la opcion de registrarte y asi poder obtener los permisos propios de cualquier usuario registrado. Añadiremos una cuenta especial, que sera con un correo especial, que sera la cuneta de administrador, el cual podra hacer funciones especiales y administrar la pagina.

![image](https://github.com/user-attachments/assets/9e8aa25e-17c5-4e44-9304-8bb01ea0e974)

La primera seccion de nuetra aplicacion web, encontramos la seccion de clubbing, la cual muestra las tres fiestas que se celebrar semanalmente en nuestra sala, el usuario podra elegir cualquiera de la tres para ver tematica, hora, precio, estadisticas varias, y en caso de estar registrado y loggeado, podra adquirir su entrada.

![image](https://github.com/user-attachments/assets/6393e50c-056c-4fb6-b432-12e82a4391a6)
![image](https://github.com/user-attachments/assets/ecdcd8c2-b5ba-4d1b-a822-38cf6106cdce)

En esta seccion, encontramos los conciertos disponibles, donde el usuario podra elegir filtrar entre generos musicales, o navegar y ver todos los que hay disponibles.
Si quiere consultar mas detalles de alguno de ellos, podra pinchar en el boton y consultar mas informacion, y en caso de estar registrado, comprar una o varias entradas.

![image](https://github.com/user-attachments/assets/d7817bcd-0e5d-4e08-9ae1-82750f463a6f)
![image](https://github.com/user-attachments/assets/74e46d91-c6d9-4e3d-81ac-319de482c991)
![image](https://github.com/user-attachments/assets/243dc40f-a1ca-476b-b115-984fd4d1496b)

En la parte de las pagina detalle, encontramos una foto del concierto en grande, con una descripcion y atributos del evento, ademas de un boton que te dejara comprar la entrada si estas registrado.
Abajo encontramos los comentarios de la gente, con valoraciones.

![image](https://github.com/user-attachments/assets/0a22dd24-d2ed-441d-b592-7806f45de59a) 

Por ultimo encontramos la seccion de solicitud de evento corporativo, destinado a jefes o direccion de empresas, que quieran realizar algun tipo de evento para sus trabajadores, etc.
El solicitante debera rellenar el fomrulario, y se le generara un pdf, con el presupuesto en base a lo que haya solicitado.

![image](https://github.com/user-attachments/assets/b0f6e028-e132-41a1-b089-786f811a0848)

Por ultimo encontramos un footer sencillo, con distina infomracion de la sala y la empresa que la dirige, ademas de los links a las RRSS





