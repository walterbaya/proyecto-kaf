# Sistema de Gestión de Calzado

## Descripción

Aplicación de escritorio desarrollada en JavaFX para la gestión de inventario y ventas de una tienda de calzado.

La aplicación permite:

* Importar productos desde un archivo Excel.
* Almacenar los productos en una base de datos SQLite.
* Consultar productos mediante lectura de código QR usando una webcam.
* Registrar ventas mediante lectura de código QR.
* Descontar automáticamente el stock.
* Actualizar el Excel luego de cada venta.
* Trabajar de forma local sin necesidad de internet.

---

# Tecnologías utilizadas

* Java 21
* JavaFX 21
* Maven
* SQLite
* Apache POI
* ZXing (lectura de QR)
* Webcam Capture (Sarxos)

---

# Estructura del Proyecto

src/main/java

app/

* App.java

controller/

* MainController.java
* BuscarController.java
* VentaController.java

dao/

* ProductoDAO.java

database/

* DatabaseManager.java

model/

* Producto.java

service/

* ExcelService.java
* QRScannerService.java
* VentaService.java
* WebcamManager.java

util/

* ImageUtils.java

src/main/resources

* MainView.fxml
* BuscarView.fxml
* VentaView.fxml
* styles.css

---

# Modelo de Datos

Tabla productos

| Campo       | Tipo    |
| ----------- | ------- |
| qr          | TEXT    |
| codigo      | TEXT    |
| descripcion | TEXT    |
| marca       | TEXT    |
| talle       | TEXT    |
| color       | TEXT    |
| stock       | INTEGER |

---

# Formato Excel

Nombre sugerido:

productos.xlsx

Columnas obligatorias:

QR
Codigo
Descripcion
Marca
Talle
Color
Stock

Ejemplo:

QR,Codigo,Descripcion,Marca,Talle,Color,Stock

123456789,DEP001,Zapatilla Running,Nike,42,Negro,5

987654321,DEP002,Zapatilla Urbana,Adidas,41,Blanco,3

---

# Flujo de Inicio

Al iniciar la aplicación:

1. Se crea la base SQLite si no existe.
2. Se verifica si existen productos cargados.
3. Se importa automáticamente el Excel.
4. Los productos quedan disponibles para búsqueda y venta.

---

# Consulta de Productos

Menú Principal

→ Buscar Producto

Funcionamiento:

1. La webcam se activa.
2. Se escanea un código QR.
3. El sistema busca el QR en la base de datos.
4. Se muestran:

* Código
* Descripción
* Marca
* Talle
* Color
* Stock

---

# Venta de Productos

Menú Principal

→ Realizar Venta

Funcionamiento:

1. Se escanea un QR.
2. Se localiza el producto.
3. Se descuenta 1 unidad de stock.
4. Se actualiza SQLite.
5. Se actualiza el Excel.
6. Se muestra confirmación.

---

# Webcam

La aplicación utiliza una única instancia compartida.

Clase:

service.WebcamManager

Objetivo:

* Evitar múltiples aperturas.
* Evitar WebcamLockException.
* Compartir la misma webcam entre ventanas.

---

# Estilos

Archivo:

styles.css

Características:

* Botones grandes.
* Tipografía grande.
* Diseño orientado a mostrador.
* Interfaz optimizada para pantallas Full HD.

---

# Dependencias Maven

JavaFX
SQLite JDBC
Apache POI
ZXing
Webcam Capture

---

# Ejecución en Desarrollo

Compilar:

mvn clean package

Ejecutar:

mvn javafx:run

---

# Generación de Ejecutable

Compilar:

mvn clean package

Generar EXE:

jpackage ^
--input target ^
--name CalzadoApp ^
--main-jar calzado-app-1.0.jar ^
--main-class app.App ^
--type exe ^
--dest release

Resultado:

release/

CalzadoApp.exe

---

# Estructura Recomendada de Producción

CalzadoApp/

CalzadoApp.exe

data/

productos.xlsx

inventario.db

---

# Funcionalidades Futuras

* Historial de ventas.
* Reportes PDF.
* Exportación Excel.
* Múltiples usuarios.
* Control de stock mínimo.
* Alertas de reposición.
* Dashboard de ventas.
* Impresión de tickets.
* Generación de QR.
* Gestión de proveedores.

---

# Autor

Proyecto desarrollado para gestión de inventario y ventas de una tienda de calzado utilizando JavaFX, SQLite, Excel y lectura de QR mediante webcam.
