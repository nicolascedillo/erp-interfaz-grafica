# 🧪 Práctica de Gestión de Compras ERP
## 📌 Información General

- **Título:** Desarrollo de Interrfaz Grafica de Sistema de Gestión de Compras ERP
- **Asignatura:** Programación Orientada a Objetos
- **Carrera:** Computación
- **Estudiantes:** Nicolas Cedillo & Mateo Miller
- **Fecha:** 05 / 25 / 2025
- **Profesor:** Gabriel León

---

## 🛠️ Descripción

Este proyecto en Java simula un sistema de gestión de compras para una empresa. El sistema automatiza la gestión de proveedores, productos y solicitudes de compra, todo bajo un enfoque de programación orientada a objetos.

El proyecto permite:

- Registrar proveedores y productos que estos ofrecen.
- Crear y gestionar solicitudes de compra.
- Controlar el estado de cada solicitud (SOLICITADA, EN_REVISIÓN, APROBADA, RECHAZADA).
- Calcular el total de la compra dentro de cada solicitud.
- Interactuar con el sistema a través de un menú grafico mejorando la UX asi como UI.

El sistema implementa diversos conceptos de la programación orientada a objetos, como herencia, interfaces, clases abstractas, polimorfismo y enumeraciones.

---

## 🧪 Estructura
```bash
ec.edu.ups.poo/
│
├── models/
│   ├── enums/
│   │   ├── EstadoSolicitud.java
│   │   ├── Feriado.java
│   │   └── Rol.java
│   │
│   ├── interfaces/
│   │   └── Calculable.java
│   │
│   ├── entities/
│   │   ├── Persona.java
│   │   ├── Empleado.java
│   │   ├── Provedor.java
│   │   └── Departamento.java
│   │
│   ├── inventory/
│   │   ├── Producto.java
│   │   ├── ProductoConIva.java
│   │   └── ProductoSinIva.java
│   │
│   └── purchases/
│       ├── SolicitudCompra.java
│       └── DetalleSolicitud.java
│
├── data/
│   └── Datos.java
│
├── views/
│   ├── VentanaAgregarProducto.java
│   ├── VentanaAgregarProductoParaProveedor.java
│   ├── VentanaAgregarProveedor.java
│   ├── VentanaAgregarSolicitud.java
│   ├── VentanaBuscarProductoID.java
│   ├── VentanaBuscarProductoNombre.java
│   ├── VentanaBuscarProveedorID.java
│   ├── VentanaBuscarProveedorNombre.java
│   ├── VentanaBuscarSolicitudEstado.java
│   ├── VentanaBuscarSolicitudID.java
│   ├── VentanaCambiarEstadoSolicitud.java
│   ├── VentanaListarProducto.java
│   ├── VentanaListarProveedor.java
│   ├── VentanaListarSolicitud.java
│   ├── VentanaLogin.java
│   └── VentanaMenu.java
│
└── Main.java
```
---

## 🗂️ Diagrama
![](https://raw.githubusercontent.com/MJMMiller/POO_DIAGRAMS/refs/heads/main/Diagrama_sis_erp_act.jpg)

---
## 🚀 Ejecución

Para ejecutar el proyecto:

1. **Compila el código:**
    ```bash
    javac App.java
    ```

2. **Ejecuta la aplicación:**
    ```bash
    java App
    ```

3. **Interacción a través del menú:**

## 🍔 Menú
Este es un menu proporcioado acorde al rol, se asignaran diferentes funcionalidades: 

    - Registrar Proveedor: Registrar un nuevo proveedor en el sistema.  
    - Registrar Producto: Registrar un nuevo producto en el sistema.  
    - Registrar Solicitud: Registrar una nueva solicitud de compra.  
    - Listar Proveedores: Ver la lista de todos los proveedores.  
    - Listar Productos: Ver la lista de todos los productos.  
    - Listar Solicitudes de Compra: Ver las solicitudes de compra realizadas.  
    - Buscar proveedor por ID: Buscar un proveedor específico por su ID.  
    - Buscar proveedor por Nombre: Buscar un proveedor específico por su nombre.  
    - Buscar producto por ID: Buscar un producto específico por su ID.  
    - Buscar producto por Nombre: Buscar un producto específico por su nombre.  
    - Buscar solicitud por ID: Buscar una solicitud específica por su ID.  
    - Buscar solicitud por Estado: Buscar solicitudes según su estado.  
    - Cambiar estado solicitud de compra: Cambiar el estado de una solicitud de compra.  
    - Cerrar Sesion: Salir del menú.

---

## 🧑‍💻 Ejemplo de Salida

![](https://raw.githubusercontent.com/MJMMiller/POO_RESULTADOS/refs/heads/main/InterfazGrafica-ERP.png)

---

## 📚 Tecnologías Utilizadas

- **Lenguaje de Programación:** 
  - Java
- **Conceptos de POO Aplicados:**
  - Herencia
  - Polimorfismo
  - Clases abstractas
  - Interfaces
  - Enumeraciones

---

## 👥 Colaboradores

- **Nicolas Cedillo - [@nicolascedillo](https://github.com/nicolascedillo)**
- **Mateo Miller - [@MJMMiller](https://github.com/MJMMiller)**

