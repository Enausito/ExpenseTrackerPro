# ExpenseTracker Pro

## Descripción del Proyecto
ExpenseTracker Pro es una aplicación Android nativa diseñada para la gestión eficiente de finanzas personales. Su objetivo principal es ofrecer una herramienta accesible, ligera y funcional que permita a los usuarios registrar sus gastos diarios, categorizarlos y visualizarlos mediante un dashboard intuitivo. A diferencia de soluciones complejas, esta aplicación prioriza la simplicidad y el almacenamiento transparente de datos en texto plano.

## Exposición del Problema
Muchas personas encuentran difícil mantener un control de sus finanzas debido a que las aplicaciones existentes son demasiado complejas, requieren conexión permanente a internet o consumen demasiados recursos. Existe una necesidad de una herramienta "offline-first", rápida y que funcione fluidamente en dispositivos de gama media/baja, permitiendo al usuario tener control total y legible de sus propios datos sin dependencias ocultas.

## Plataforma y Tecnologías
* **Sistema Operativo:** Android (Min SDK 26 - Android 8.0+).
* **Lenguaje:** Kotlin.
* **Arquitectura:** MVVM (Model-View-ViewModel).
* **Persistencia de Datos:** Sistema de archivos local (Lectura/Escritura en `.txt` plano) para máxima transparencia y portabilidad.
* **UI:** Material Design con XML/ViewBinding.
* **Asincronía:** Kotlin Coroutines.

## Interfaz de Usuario e Interfaz de Administrador
* **UI de Usuario:** * Dashboard visual con resumen de gastos.
    * Botón de acción flotante (FAB) para registro rápido.
    * Listado de historial con funcionalidad "Swipe-to-delete" (deslizar para borrar).
    * Soporte multi-idioma (Español/Inglés).
* **UI de Administrador:** * Acceso diferenciado.
    * Capacidad de exportar el archivo de registro `.txt` crudo.
    * Gestión de categorías personalizadas.

## Funcionalidad
1.  **Registro:** Captura de monto, categoría, descripción y fecha.
2.  **Persistencia:** Guardado automático en `gastos.txt` en el almacenamiento interno.
3.  **Visualización:** Cálculo automático de totales y renderizado de lista.
4.  **Admin:** Herramientas de depuración y visualización directa del archivo plano.

## Diseño (Wireframes)
* **Pantalla Principal:** Header con Total Gastado | Gráfico Circular | Lista de Últimos Movimientos.
* **Pantalla Agregar:** Input Monto | Spinner Categoría | Input Nota | Botón Guardar.
* **Pantalla Admin:** Visor de Logs | Botón Exportar TXT | Configuración de Límites.

---
**Versión:** 1.0.0
**Desarrollado por:** [Enausito]
