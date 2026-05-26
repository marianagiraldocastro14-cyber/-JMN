package co.edu.cesde.ga.application;

import co.edu.cesde.ga.model.*;
import co.edu.cesde.ga.repository.*;
import co.edu.cesde.ga.repository.impl.*;
import co.edu.cesde.ga.service.*;
import co.edu.cesde.ga.service.impl.*;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    // Repositorios
    private static final StudentRepository studentRepository       = new StudentRepositoryInMemory();
    private static final TeacherRepository teacherRepository       = new TeacherRepositoryInMemory();
    private static final UsersRepository usersRepository           = new UsersRepositoryInMemory();
    private static final RolesRepository rolesRepository           = new RolesRepositoryInMemory();
    private static final ProgramsRepository programsRepository     = new ProgramsRepositoryInMemory();
    private static final SubjectRepository subjectRepository       = new SubjectRepositoryInMemory();
    private static final PeriodRepository periodRepository         = new PeriodRepositoryInMemory();
    private static final GroupRepository groupRepository           = new GroupRepositoryInMemory();
    private static final EnrollmentsRepository enrollmentsRepository = new EnrollmentsRepositoryInMemory();
    private static final GradesRepository gradesRepository         = new GradesRepositoryInMemory();
    private static final UserRolesRepository userRolesRepository   = new UserRolesRepositoryInMemory();

    // Servicios
    private static final StudentService    studentService    = new StudentServiceimpl(studentRepository);
    private static final TeacherService    teacherService    = new TeacherServiceImpl(teacherRepository);
    private static final UsersService      usersService      = new UsersServiceImpl(usersRepository);
    private static final RolesService      rolesService      = new RolesServiceImpl(rolesRepository);
    private static final ProgramsService   programsService   = new ProgramsServiceImpl(programsRepository);
    private static final SubjectService    subjectService    = new SubjectServiceImpl(subjectRepository);
    private static final PeriodService     periodService     = new PeriodServiceImpl(periodRepository);
    private static final GroupService      groupService      = new GroupServiceImpl(groupRepository);
    private static final EnrollmentsService enrollmentsService = new EnrollmentsServiceImpl(enrollmentsRepository);
    private static final GradesService     gradesService     = new GradesServiceImpl(gradesRepository);
    private static final UserRolesService  userRolesService  = new UserRolesServiceImpl(userRolesRepository);

    public static void main(String[] args) {
        showMainMenu();
    }

    // =========================================================
    // MENU PRINCIPAL
    // =========================================================

    private static void showMainMenu() {
        int option;
        do {
            System.out.println("\n===== SISTEMA ACADEMICO CESDE 2026 =====");
            System.out.println("1.  Gestion de estudiantes");
            System.out.println("2.  Gestion de profesores");
            System.out.println("3.  Gestion de usuarios");
            System.out.println("4.  Gestion de roles");
            System.out.println("5.  Gestion de programas");
            System.out.println("6.  Gestion de materias");
            System.out.println("7.  Gestion de periodos");
            System.out.println("8.  Gestion de grupos");
            System.out.println("9.  Gestion de inscripciones");
            System.out.println("10. Gestion de calificaciones");
            System.out.println("11. Gestion de roles de usuario");
            System.out.println("0.  Salir");
            System.out.print("Seleccione una opcion: ");
            option = readInt();
            switch (option) {
                case 1  -> showStudentMenu();
                case 2  -> showTeacherMenu();
                case 3  -> showUserMenu();
                case 4  -> showRoleMenu();
                case 5  -> showProgramMenu();
                case 6  -> showSubjectMenu();
                case 7  -> showPeriodMenu();
                case 8  -> showGroupMenu();
                case 9  -> showEnrollmentMenu();
                case 10 -> showGradesMenu();
                case 11 -> showUserRolesMenu();
                case 0  -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opcion invalida.");
            }
        } while (option != 0);
    }

    // =========================================================
    // ESTUDIANTES
    // =========================================================

    private static void showStudentMenu() {
        int option;
        do {
            System.out.println("\n===== SUBMENU ESTUDIANTES =====");
            System.out.println("1. Crear estudiante");
            System.out.println("2. Listar estudiantes");
            System.out.println("3. Buscar por ID");
            System.out.println("4. Buscar por numero de documento");
            System.out.println("5. Actualizar estudiante");
            System.out.println("6. Eliminar estudiante");
            System.out.println("7. Total de estudiantes");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opcion: ");
            option = readInt();
            switch (option) {
                case 1 -> executeAction(Main::createStudent);
                case 2 -> executeAction(Main::listStudents);
                case 3 -> executeAction(Main::findStudentById);
                case 4 -> executeAction(Main::findStudentByDocumentNumber);
                case 5 -> executeAction(Main::updateStudent);
                case 6 -> executeAction(Main::deleteStudent);
                case 7 -> System.out.println("Total estudiantes: " + studentRepository.count());
                case 0 -> System.out.println("Regresando...");
                default -> System.out.println("Opcion invalida.");
            }
        } while (option != 0);
    }

    private static void createStudent() {
        System.out.println("\n--- Crear estudiante ---");
        String documentType   = readRequiredString("Tipo de documento (CC/TI/PAS): ");
        String documentNumber = readRequiredString("Numero de documento: ");
        String firstName      = readRequiredString("Nombres: ");
        String lastName       = readRequiredString("Apellidos: ");
        String birthDate      = readRequiredString("Fecha de nacimiento (YYYY-MM-DD): ");
        String status         = readRequiredString("Estado (ACTIVO/INACTIVO): ");
        Long   userId         = readOptionalLong("User ID (opcional, Enter para omitir): ");

        Student student = new Student(userId, documentType, documentNumber, firstName, lastName, status, birthDate);
        Student created = studentService.create(student);
        System.out.println("Estudiante creado:\n" + created);
    }

    private static void listStudents() {
        System.out.println("\n--- Lista de estudiantes ---");
        List<Student> students = studentRepository.findAll();
        if (students.isEmpty()) { System.out.println("No hay estudiantes registrados."); return; }
        students.forEach(System.out::println);
    }

    private static void findStudentById() {
        Long id = readLong("Student ID: ");
        if (id == null) { System.out.println("ID invalido."); return; }
        System.out.println(studentService.findById(id));
    }

    private static void findStudentByDocumentNumber() {
        String doc = readRequiredString("Numero de documento: ");
        Student s = studentRepository.findByDocumentNumber(doc);
        if (s == null) { System.out.println("No se encontro estudiante con ese documento."); return; }
        System.out.println(s);
    }

    private static void updateStudent() {
        Long id = readLong("Student ID a actualizar: ");
        if (id == null) { System.out.println("ID invalido."); return; }
        Student s = studentService.findById(id);
        System.out.println("Presione Enter para conservar el valor actual.");

        String docType  = readOptionalString("Tipo de documento [" + s.getDocumentType() + "]: ");
        String docNum   = readOptionalString("Numero de documento [" + s.getDocumentNumber() + "]: ");
        String first    = readOptionalString("Nombres [" + s.getFirstName() + "]: ");
        String last     = readOptionalString("Apellidos [" + s.getLastName() + "]: ");
        String birth    = readOptionalString("Fecha de nacimiento [" + s.getBirthDate() + "]: ");
        String status   = readOptionalString("Estado [" + s.getStatus() + "]: ");
        Long   userId   = readOptionalLongWithCurrent("User ID [" + (s.getUserId() == null ? "null" : s.getUserId()) + "] (0 para limpiar): ");

        Long newUserId = s.getUserId();
        if (userId != Long.MIN_VALUE) newUserId = userId == 0L ? null : userId;

        Student updated = new Student(
                s.getStudentId(),
                birth.isBlank()   ? s.getBirthDate()      : birth,
                newUserId,
                s.getCode(),
                docType.isBlank() ? s.getDocumentType()   : docType,
                docNum.isBlank()  ? s.getDocumentNumber() : docNum,
                first.isBlank()   ? s.getFirstName()      : first,
                last.isBlank()    ? s.getLastName()        : last,
                status.isBlank()  ? s.getStatus()         : status
        );
        if (studentService.update(updated)) System.out.println("Actualizado:\n" + updated);
        else System.out.println("No fue posible actualizar.");
    }

    private static void deleteStudent() {
        Long id = readLong("Student ID a eliminar: ");
        if (id == null) { System.out.println("ID invalido."); return; }
        if (studentService.delete(id)) System.out.println("Estudiante eliminado.");
        else System.out.println("No existe un estudiante con ese ID.");
    }

    // =========================================================
    // PROFESORES
    // =========================================================

    private static void showTeacherMenu() {
        int option;
        do {
            System.out.println("\n===== SUBMENU PROFESORES =====");
            System.out.println("1. Crear profesor");
            System.out.println("2. Listar profesores");
            System.out.println("3. Buscar por ID");
            System.out.println("4. Actualizar profesor");
            System.out.println("5. Eliminar profesor");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opcion: ");
            option = readInt();
            switch (option) {
                case 1 -> executeAction(Main::createTeacher);
                case 2 -> executeAction(Main::listTeachers);
                case 3 -> executeAction(Main::findTeacherById);
                case 4 -> executeAction(Main::updateTeacher);
                case 5 -> executeAction(Main::deleteTeacher);
                case 0 -> System.out.println("Regresando...");
                default -> System.out.println("Opcion invalida.");
            }
        } while (option != 0);
    }

    private static void createTeacher() {
        System.out.println("\n--- Crear profesor ---");
        Long   userId         = readOptionalLong("User ID (opcional, Enter para omitir): ");
        String code           = readOptionalString("Codigo (opcional): ");
        String documentType   = readRequiredString("Tipo de documento (CC/TI/PAS): ");
        String documentNumber = readRequiredString("Numero de documento: ");
        String firstName      = readRequiredString("Nombres: ");
        String lastName       = readRequiredString("Apellidos: ");
        String status         = readRequiredString("Estado (ACTIVO/INACTIVO): ");

        Teacher t = new Teacher(userId, code.isBlank() ? null : code, documentType, documentNumber, firstName, lastName, status);
        System.out.println("Profesor creado:\n" + teacherService.create(t));
    }

    private static void listTeachers() {
        System.out.println("\n--- Lista de profesores ---");
        List<Teacher> list = teacherRepository.findAll();
        if (list.isEmpty()) { System.out.println("No hay profesores registrados."); return; }
        list.forEach(System.out::println);
    }

    private static void findTeacherById() {
        Long id = readLong("Teacher ID: ");
        if (id == null) { System.out.println("ID invalido."); return; }
        System.out.println(teacherService.findById(id));
    }

    private static void updateTeacher() {
        Long id = readLong("Teacher ID a actualizar: ");
        if (id == null) { System.out.println("ID invalido."); return; }
        Teacher t = teacherService.findById(id);
        System.out.println("Presione Enter para conservar el valor actual.");

        String docType  = readOptionalString("Tipo de documento [" + t.getDocumentType() + "]: ");
        String docNum   = readOptionalString("Numero de documento [" + t.getDocumentNumber() + "]: ");
        String first    = readOptionalString("Nombres [" + t.getFirstName() + "]: ");
        String last     = readOptionalString("Apellidos [" + t.getLastName() + "]: ");
        String status   = readOptionalString("Estado [" + t.getStatus() + "]: ");

        Teacher updated = new Teacher(
                t.getTeacherId(),
                t.getUserId(),
                t.getCode(),
                docType.isBlank() ? t.getDocumentType()   : docType,
                docNum.isBlank()  ? t.getDocumentNumber() : docNum,
                first.isBlank()   ? t.getFirstName()      : first,
                last.isBlank()    ? t.getLastName()        : last,
                status.isBlank()  ? t.getStatus()         : status
        );
        if (teacherService.update(updated)) System.out.println("Actualizado:\n" + updated);
        else System.out.println("No fue posible actualizar.");
    }

    private static void deleteTeacher() {
        Long id = readLong("Teacher ID a eliminar: ");
        if (id == null) { System.out.println("ID invalido."); return; }
        if (teacherService.delete(id)) System.out.println("Profesor eliminado.");
        else System.out.println("No existe un profesor con ese ID.");
    }

    // =========================================================
    // USUARIOS
    // =========================================================

    private static void showUserMenu() {
        int option;
        do {
            System.out.println("\n===== SUBMENU USUARIOS =====");
            System.out.println("1. Crear usuario");
            System.out.println("2. Listar usuarios");
            System.out.println("3. Buscar por ID");
            System.out.println("4. Buscar por username");
            System.out.println("5. Actualizar usuario");
            System.out.println("6. Eliminar usuario");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opcion: ");
            option = readInt();
            switch (option) {
                case 1 -> executeAction(Main::createUser);
                case 2 -> executeAction(Main::listUsers);
                case 3 -> executeAction(Main::findUserById);
                case 4 -> executeAction(Main::findUserByUsername);
                case 5 -> executeAction(Main::updateUser);
                case 6 -> executeAction(Main::deleteUser);
                case 0 -> System.out.println("Regresando...");
                default -> System.out.println("Opcion invalida.");
            }
        } while (option != 0);
    }

    private static void createUser() {
        System.out.println("\n--- Crear usuario ---");
        String username = readRequiredString("Username: ");
        String email    = readRequiredString("Email: ");
        String password = readRequiredString("Password: ");
        String status   = readRequiredString("Estado (ACTIVO/INACTIVO): ");
        String created  = readRequiredString("Fecha de creacion (YYYY-MM-DD): ");
        Users u = new Users(username, email, password, status, created);
        System.out.println("Usuario creado:\n" + usersService.create(u));
    }

    private static void listUsers() {
        System.out.println("\n--- Lista de usuarios ---");
        List<Users> list = usersRepository.findAll();
        if (list.isEmpty()) { System.out.println("No hay usuarios registrados."); return; }
        list.forEach(System.out::println);
    }

    private static void findUserById() {
        Long id = readLong("User ID: ");
        if (id == null) { System.out.println("ID invalido."); return; }
        System.out.println(usersService.findById(id));
    }

    private static void findUserByUsername() {
        String username = readRequiredString("Username: ");
        System.out.println(usersService.findByUsername(username));
    }

    private static void updateUser() {
        Long id = readLong("User ID a actualizar: ");
        if (id == null) { System.out.println("ID invalido."); return; }
        Users u = usersService.findById(id);
        System.out.println("Presione Enter para conservar el valor actual.");

        String username = readOptionalString("Username [" + u.getUsername() + "]: ");
        String email    = readOptionalString("Email [" + u.getEmail() + "]: ");
        String password = readOptionalString("Password (Enter conserva actual): ");
        String status   = readOptionalString("Estado [" + u.getStatus() + "]: ");
        String created  = readOptionalString("Fecha de creacion [" + u.getCreatedAt() + "]: ");

        Users updated = new Users(
                u.getUserId(),
                username.isBlank() ? u.getUsername()     : username,
                email.isBlank()    ? u.getEmail()        : email,
                password.isBlank() ? u.getPasswordHash() : password,
                status.isBlank()   ? u.getStatus()       : status,
                created.isBlank()  ? u.getCreatedAt()    : created
        );
        if (usersService.update(updated)) System.out.println("Actualizado:\n" + updated);
        else System.out.println("No fue posible actualizar.");
    }

    private static void deleteUser() {
        Long id = readLong("User ID a eliminar: ");
        if (id == null) { System.out.println("ID invalido."); return; }
        if (usersService.delete(id)) System.out.println("Usuario eliminado.");
        else System.out.println("No existe un usuario con ese ID.");
    }

    // =========================================================
    // ROLES
    // =========================================================

    private static void showRoleMenu() {
        int option;
        do {
            System.out.println("\n===== SUBMENU ROLES =====");
            System.out.println("1. Crear rol");
            System.out.println("2. Listar roles");
            System.out.println("3. Buscar por ID");
            System.out.println("4. Buscar por nombre");
            System.out.println("5. Actualizar rol");
            System.out.println("6. Eliminar rol");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opcion: ");
            option = readInt();
            switch (option) {
                case 1 -> executeAction(Main::createRole);
                case 2 -> executeAction(Main::listRoles);
                case 3 -> executeAction(Main::findRoleById);
                case 4 -> executeAction(Main::findRoleByName);
                case 5 -> executeAction(Main::updateRole);
                case 6 -> executeAction(Main::deleteRole);
                case 0 -> System.out.println("Regresando...");
                default -> System.out.println("Opcion invalida.");
            }
        } while (option != 0);
    }

    private static void createRole() {
        System.out.println("\n--- Crear rol ---");
        String name = readRequiredString("Nombre: ");
        String desc = readRequiredString("Descripcion: ");
        System.out.println("Rol creado:\n" + rolesService.create(new Roles(name, desc)));
    }

    private static void listRoles() {
        System.out.println("\n--- Lista de roles ---");
        List<Roles> list = rolesRepository.findAll();
        if (list.isEmpty()) { System.out.println("No hay roles registrados."); return; }
        list.forEach(System.out::println);
    }

    private static void findRoleById() {
        Long id = readLong("Role ID: ");
        if (id == null) { System.out.println("ID invalido."); return; }
        System.out.println(rolesService.findById(id));
    }

    private static void findRoleByName() {
        String name = readRequiredString("Nombre del rol: ");
        System.out.println(rolesService.findByName(name));
    }

    private static void updateRole() {
        Long id = readLong("Role ID a actualizar: ");
        if (id == null) { System.out.println("ID invalido."); return; }
        Roles r = rolesService.findById(id);
        System.out.println("Presione Enter para conservar el valor actual.");
        String name = readOptionalString("Nombre [" + r.getName() + "]: ");
        String desc = readOptionalString("Descripcion [" + r.getDescription() + "]: ");
        Roles updated = new Roles(
                r.getRolesId(),
                name.isBlank() ? r.getName()        : name,
                desc.isBlank() ? r.getDescription() : desc
        );
        if (rolesService.update(updated)) System.out.println("Actualizado:\n" + updated);
        else System.out.println("No fue posible actualizar.");
    }

    private static void deleteRole() {
        Long id = readLong("Role ID a eliminar: ");
        if (id == null) { System.out.println("ID invalido."); return; }
        if (rolesService.delete(id)) System.out.println("Rol eliminado.");
        else System.out.println("No existe un rol con ese ID.");
    }

    // =========================================================
    // PROGRAMAS
    // =========================================================

    private static void showProgramMenu() {
        int option;
        do {
            System.out.println("\n===== SUBMENU PROGRAMAS =====");
            System.out.println("1. Crear programa");
            System.out.println("2. Listar programas");
            System.out.println("3. Buscar por ID");
            System.out.println("4. Actualizar programa");
            System.out.println("5. Eliminar programa");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opcion: ");
            option = readInt();
            switch (option) {
                case 1 -> executeAction(Main::createProgram);
                case 2 -> executeAction(Main::listPrograms);
                case 3 -> executeAction(Main::findProgramById);
                case 4 -> executeAction(Main::updateProgram);
                case 5 -> executeAction(Main::deleteProgram);
                case 0 -> System.out.println("Regresando...");
                default -> System.out.println("Opcion invalida.");
            }
        } while (option != 0);
    }

    private static void createProgram() {
        System.out.println("\n--- Crear programa ---");
        String code = readRequiredString("Codigo: ");
        String name = readRequiredString("Nombre: ");
        System.out.println("Programa creado:\n" + programsService.create(new Programs(code, name)));
    }

    private static void listPrograms() {
        System.out.println("\n--- Lista de programas ---");
        List<Programs> list = programsRepository.findAll();
        if (list.isEmpty()) { System.out.println("No hay programas registrados."); return; }
        list.forEach(System.out::println);
    }

    private static void findProgramById() {
        Long id = readLong("Program ID: ");
        if (id == null) { System.out.println("ID invalido."); return; }
        System.out.println(programsService.findById(id));
    }

    private static void updateProgram() {
        Long id = readLong("Program ID a actualizar: ");
        if (id == null) { System.out.println("ID invalido."); return; }
        Programs p = programsService.findById(id);
        System.out.println("Presione Enter para conservar el valor actual.");
        String code = readOptionalString("Codigo [" + p.getCode() + "]: ");
        String name = readOptionalString("Nombre [" + p.getName() + "]: ");
        Programs updated = new Programs(
                p.getProgramId(),
                code.isBlank() ? p.getCode() : code,
                name.isBlank() ? p.getName() : name
        );
        if (programsService.update(updated)) System.out.println("Actualizado:\n" + updated);
        else System.out.println("No fue posible actualizar.");
    }

    private static void deleteProgram() {
        Long id = readLong("Program ID a eliminar: ");
        if (id == null) { System.out.println("ID invalido."); return; }
        if (programsService.delete(id)) System.out.println("Programa eliminado.");
        else System.out.println("No existe un programa con ese ID.");
    }

    // =========================================================
    // MATERIAS
    // =========================================================

    private static void showSubjectMenu() {
        int option;
        do {
            System.out.println("\n===== SUBMENU MATERIAS =====");
            System.out.println("1. Crear materia");
            System.out.println("2. Listar materias");
            System.out.println("3. Buscar por ID");
            System.out.println("4. Buscar por codigo");
            System.out.println("5. Actualizar materia");
            System.out.println("6. Eliminar materia");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opcion: ");
            option = readInt();
            switch (option) {
                case 1 -> executeAction(Main::createSubject);
                case 2 -> executeAction(Main::listSubjects);
                case 3 -> executeAction(Main::findSubjectById);
                case 4 -> executeAction(Main::findSubjectByCode);
                case 5 -> executeAction(Main::updateSubject);
                case 6 -> executeAction(Main::deleteSubject);
                case 0 -> System.out.println("Regresando...");
                default -> System.out.println("Opcion invalida.");
            }
        } while (option != 0);
    }

    private static void createSubject() {
        System.out.println("\n--- Crear materia ---");
        String code      = readRequiredString("Codigo: ");
        String name      = readRequiredString("Nombre: ");
        Integer credits  = readInteger("Creditos: ");
        Long programId   = readLong("Program ID: ");
        if (credits == null || programId == null) { System.out.println("Datos invalidos."); return; }
        System.out.println("Materia creada:\n" + subjectService.create(new Subject(code, name, credits, programId)));
    }

    private static void listSubjects() {
        System.out.println("\n--- Lista de materias ---");
        List<Subject> list = subjectRepository.findAll();
        if (list.isEmpty()) { System.out.println("No hay materias registradas."); return; }
        list.forEach(System.out::println);
    }

    private static void findSubjectById() {
        Long id = readLong("Subject ID: ");
        if (id == null) { System.out.println("ID invalido."); return; }
        System.out.println(subjectService.findById(id));
    }

    private static void findSubjectByCode() {
        String code = readRequiredString("Codigo: ");
        System.out.println(subjectService.findByCode(code));
    }

    private static void updateSubject() {
        Long id = readLong("Subject ID a actualizar: ");
        if (id == null) { System.out.println("ID invalido."); return; }
        Subject s = subjectService.findById(id);
        System.out.println("Presione Enter para conservar el valor actual.");
        String code    = readOptionalString("Codigo [" + s.getCode() + "]: ");
        String name    = readOptionalString("Nombre [" + s.getName() + "]: ");
        String credStr = readOptionalString("Creditos [" + s.getCredits() + "]: ");
        String pidStr  = readOptionalString("Program ID [" + s.getProgramId() + "]: ");

        Integer credits   = credStr.isBlank() ? s.getCredits()   : parseInteger(credStr, s.getCredits());
        Long    programId = pidStr.isBlank()  ? s.getProgramId() : parseLong(pidStr,    s.getProgramId());

        Subject updated = new Subject(
                s.getSubjectId(),
                code.isBlank() ? s.getCode() : code,
                name.isBlank() ? s.getName() : name,
                credits,
                programId
        );
        if (subjectService.update(updated)) System.out.println("Actualizado:\n" + updated);
        else System.out.println("No fue posible actualizar.");
    }

    private static void deleteSubject() {
        Long id = readLong("Subject ID a eliminar: ");
        if (id == null) { System.out.println("ID invalido."); return; }
        if (subjectService.delete(id)) System.out.println("Materia eliminada.");
        else System.out.println("No existe una materia con ese ID.");
    }

    // =========================================================
    // PERIODOS
    // =========================================================

    private static void showPeriodMenu() {
        int option;
        do {
            System.out.println("\n===== SUBMENU PERIODOS =====");
            System.out.println("1. Crear periodo");
            System.out.println("2. Listar periodos");
            System.out.println("3. Buscar por ID");
            System.out.println("4. Actualizar periodo");
            System.out.println("5. Eliminar periodo");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opcion: ");
            option = readInt();
            switch (option) {
                case 1 -> executeAction(Main::createPeriod);
                case 2 -> executeAction(Main::listPeriods);
                case 3 -> executeAction(Main::findPeriodById);
                case 4 -> executeAction(Main::updatePeriod);
                case 5 -> executeAction(Main::deletePeriod);
                case 0 -> System.out.println("Regresando...");
                default -> System.out.println("Opcion invalida.");
            }
        } while (option != 0);
    }

    private static void createPeriod() {
        System.out.println("\n--- Crear periodo ---");
        String code  = readRequiredString("Codigo: ");
        String start = readRequiredString("Fecha inicio (YYYY-MM-DD): ");
        String end   = readRequiredString("Fecha fin (YYYY-MM-DD): ");
        System.out.println("Periodo creado:\n" + periodService.create(new Period(code, start, end)));
    }

    private static void listPeriods() {
        System.out.println("\n--- Lista de periodos ---");
        List<Period> list = periodRepository.findAll();
        if (list.isEmpty()) { System.out.println("No hay periodos registrados."); return; }
        list.forEach(System.out::println);
    }

    private static void findPeriodById() {
        Long id = readLong("Period ID: ");
        if (id == null) { System.out.println("ID invalido."); return; }
        System.out.println(periodService.findById(id));
    }

    private static void updatePeriod() {
        Long id = readLong("Period ID a actualizar: ");
        if (id == null) { System.out.println("ID invalido."); return; }
        Period p = periodService.findById(id);
        System.out.println("Presione Enter para conservar el valor actual.");
        String code  = readOptionalString("Codigo [" + p.getCode() + "]: ");
        String start = readOptionalString("Fecha inicio [" + p.getStartDate() + "]: ");
        String end   = readOptionalString("Fecha fin [" + p.getEndDate() + "]: ");
        Period updated = new Period(
                p.getPeriodId(),
                code.isBlank()  ? p.getCode()      : code,
                start.isBlank() ? p.getStartDate() : start,
                end.isBlank()   ? p.getEndDate()   : end
        );
        if (periodService.update(updated)) System.out.println("Actualizado:\n" + updated);
        else System.out.println("No fue posible actualizar.");
    }

    private static void deletePeriod() {
        Long id = readLong("Period ID a eliminar: ");
        if (id == null) { System.out.println("ID invalido."); return; }
        if (periodService.delete(id)) System.out.println("Periodo eliminado.");
        else System.out.println("No existe un periodo con ese ID.");
    }

    // =========================================================
    // GRUPOS
    // =========================================================

    private static void showGroupMenu() {
        int option;
        do {
            System.out.println("\n===== SUBMENU GRUPOS =====");
            System.out.println("1. Crear grupo");
            System.out.println("2. Listar grupos");
            System.out.println("3. Buscar por codigo");
            System.out.println("4. Actualizar grupo");
            System.out.println("5. Eliminar grupo");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opcion: ");
            option = readInt();
            switch (option) {
                case 1 -> executeAction(Main::createGroup);
                case 2 -> executeAction(Main::listGroups);
                case 3 -> executeAction(Main::findGroupByCode);
                case 4 -> executeAction(Main::updateGroup);
                case 5 -> executeAction(Main::deleteGroup);
                case 0 -> System.out.println("Regresando...");
                default -> System.out.println("Opcion invalida.");
            }
        } while (option != 0);
    }

    private static void createGroup() {
        System.out.println("\n--- Crear grupo ---");
        String code      = readRequiredString("Codigo del grupo: ");
        Long   programId = readLong("Program ID: ");
        Long   periodId  = readLong("Period ID: ");
        String shift     = readRequiredString("Jornada (MANANA/TARDE/NOCHE): ");
        if (programId == null || periodId == null) { System.out.println("IDs invalidos."); return; }
        System.out.println("Grupo creado:\n" + groupService.create(new Group(code, programId, periodId, shift)));
    }

    private static void listGroups() {
        System.out.println("\n--- Lista de grupos ---");
        List<Group> list = groupRepository.findAll();
        if (list.isEmpty()) { System.out.println("No hay grupos registrados."); return; }
        list.forEach(System.out::println);
    }

    private static void findGroupByCode() {
        String code = readRequiredString("Codigo del grupo: ");
        System.out.println(groupService.findByCode(code));
    }

    private static void updateGroup() {
        String code = readRequiredString("Codigo del grupo a actualizar: ");
        Group g = groupService.findByCode(code);
        System.out.println("Presione Enter para conservar el valor actual.");
        String pidStr  = readOptionalString("Program ID [" + g.getProgramId() + "]: ");
        String perStr  = readOptionalString("Period ID [" + g.getPeriodId() + "]: ");
        String shift   = readOptionalString("Jornada [" + g.getShift() + "]: ");
        Long   progId  = pidStr.isBlank() ? g.getProgramId() : parseLong(pidStr, g.getProgramId());
        Long   perIdUp = perStr.isBlank() ? g.getPeriodId()  : parseLong(perStr, g.getPeriodId());
        Group updated = new Group(g.getCode(), progId, perIdUp, shift.isBlank() ? g.getShift() : shift);
        if (groupService.update(updated)) System.out.println("Actualizado:\n" + updated);
        else System.out.println("No fue posible actualizar.");
    }

    private static void deleteGroup() {
        String code = readRequiredString("Codigo del grupo a eliminar: ");
        if (groupService.delete(code)) System.out.println("Grupo eliminado.");
        else System.out.println("No existe un grupo con ese codigo.");
    }

    // =========================================================
    // INSCRIPCIONES
    // =========================================================

    private static void showEnrollmentMenu() {
        int option;
        do {
            System.out.println("\n===== SUBMENU INSCRIPCIONES =====");
            System.out.println("1. Crear inscripcion");
            System.out.println("2. Listar inscripciones");
            System.out.println("3. Buscar por ID");
            System.out.println("4. Actualizar inscripcion");
            System.out.println("5. Eliminar inscripcion");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opcion: ");
            option = readInt();
            switch (option) {
                case 1 -> executeAction(Main::createEnrollment);
                case 2 -> executeAction(Main::listEnrollments);
                case 3 -> executeAction(Main::findEnrollmentById);
                case 4 -> executeAction(Main::updateEnrollment);
                case 5 -> executeAction(Main::deleteEnrollment);
                case 0 -> System.out.println("Regresando...");
                default -> System.out.println("Opcion invalida.");
            }
        } while (option != 0);
    }

    private static void createEnrollment() {
        System.out.println("\n--- Crear inscripcion ---");
        String studentId = readRequiredString("Student ID: ");
        String groupId   = readRequiredString("Group Code: ");
        String periodId  = readRequiredString("Period ID: ");
        String status    = readRequiredString("Estado (ACTIVO/INACTIVO): ");
        String enrolled  = readRequiredString("Fecha de inscripcion (YYYY-MM-DD): ");
        System.out.println("Inscripcion creada:\n" +
                enrollmentsService.create(new Enrollments(studentId, groupId, periodId, status, enrolled)));
    }

    private static void listEnrollments() {
        System.out.println("\n--- Lista de inscripciones ---");
        List<Enrollments> list = enrollmentsService.findAll();
        if (list.isEmpty()) { System.out.println("No hay inscripciones registradas."); return; }
        list.forEach(System.out::println);
    }

    private static void findEnrollmentById() {
        Long id = readLong("Enrollment ID: ");
        if (id == null) { System.out.println("ID invalido."); return; }
        System.out.println(enrollmentsService.findById(id));
    }

    private static void updateEnrollment() {
        Long id = readLong("Enrollment ID a actualizar: ");
        if (id == null) { System.out.println("ID invalido."); return; }
        Enrollments e = enrollmentsService.findById(id);
        System.out.println("Presione Enter para conservar el valor actual.");
        String studentId = readOptionalString("Student ID [" + e.getStudentId() + "]: ");
        String groupId   = readOptionalString("Group Code [" + e.getGroupId() + "]: ");
        String periodId  = readOptionalString("Period ID [" + e.getPeriodId() + "]: ");
        String status    = readOptionalString("Estado [" + e.getStatus() + "]: ");
        String enrolled  = readOptionalString("Fecha inscripcion [" + e.getEnrolledAt() + "]: ");
        Enrollments updated = new Enrollments(
                e.getEnrollmentId(),
                studentId.isBlank() ? e.getStudentId()  : studentId,
                groupId.isBlank()   ? e.getGroupId()    : groupId,
                periodId.isBlank()  ? e.getPeriodId()   : periodId,
                status.isBlank()    ? e.getStatus()     : status,
                enrolled.isBlank()  ? e.getEnrolledAt() : enrolled
        );
        if (enrollmentsService.update(updated)) System.out.println("Actualizado:\n" + updated);
        else System.out.println("No fue posible actualizar.");
    }

    private static void deleteEnrollment() {
        Long id = readLong("Enrollment ID a eliminar: ");
        if (id == null) { System.out.println("ID invalido."); return; }
        if (enrollmentsService.delete(id)) System.out.println("Inscripcion eliminada.");
        else System.out.println("No existe una inscripcion con ese ID.");
    }

    // =========================================================
    // CALIFICACIONES
    // =========================================================

    private static void showGradesMenu() {
        int option;
        do {
            System.out.println("\n===== SUBMENU CALIFICACIONES =====");
            System.out.println("1. Crear calificacion");
            System.out.println("2. Listar calificaciones");
            System.out.println("3. Buscar por ID");
            System.out.println("4. Buscar por estudiante");
            System.out.println("5. Actualizar calificacion");
            System.out.println("6. Eliminar calificacion");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opcion: ");
            option = readInt();
            switch (option) {
                case 1 -> executeAction(Main::createGrade);
                case 2 -> executeAction(Main::listGrades);
                case 3 -> executeAction(Main::findGradeById);
                case 4 -> executeAction(Main::findGradesByStudent);
                case 5 -> executeAction(Main::updateGrade);
                case 6 -> executeAction(Main::deleteGrade);
                case 0 -> System.out.println("Regresando...");
                default -> System.out.println("Opcion invalida.");
            }
        } while (option != 0);
    }

    private static void createGrade() {
        System.out.println("\n--- Crear calificacion ---");
        Long   groupSubjectId = readLong("GroupSubject ID: ");
        Long   studentId      = readLong("Student ID: ");
        Double score          = readDouble("Nota final (0.0 - 5.0): ");
        String obs            = readRequiredString("Observacion: ");
        if (groupSubjectId == null || studentId == null || score == null) {
            System.out.println("Datos invalidos."); return;
        }
        Grades g = new Grades(groupSubjectId, studentId, score, obs);
        System.out.println("Calificacion creada:\n" + gradesService.create(g));
    }

    private static void listGrades() {
        System.out.println("\n--- Lista de calificaciones ---");
        List<Grades> list = gradesService.findAll();
        if (list.isEmpty()) { System.out.println("No hay calificaciones registradas."); return; }
        list.forEach(System.out::println);
    }

    private static void findGradeById() {
        Long id = readLong("Grade ID: ");
        if (id == null) { System.out.println("ID invalido."); return; }
        System.out.println(gradesService.findById(id));
    }

    private static void findGradesByStudent() {
        Long studentId = readLong("Student ID: ");
        if (studentId == null) { System.out.println("ID invalido."); return; }
        List<Grades> list = gradesService.findByStudentId(studentId);
        if (list.isEmpty()) { System.out.println("No hay calificaciones para ese estudiante."); return; }
        list.forEach(System.out::println);
    }

    private static void updateGrade() {
        Long id = readLong("Grade ID a actualizar: ");
        if (id == null) { System.out.println("ID invalido."); return; }
        Grades g = gradesService.findById(id);
        System.out.println("Presione Enter para conservar el valor actual.");
        String scoreStr = readOptionalString("Nota final [" + g.getFinalScore() + "]: ");
        String obs      = readOptionalString("Observacion [" + g.getObservation() + "]: ");
        Double score = scoreStr.isBlank() ? g.getFinalScore() : parseDouble(scoreStr, g.getFinalScore());
        Grades updated = new Grades(
                g.getGroupSubjectId(),
                g.getStudentId(),
                score,
                obs.isBlank() ? g.getObservation() : obs
        );
        if (gradesService.update(updated)) System.out.println("Actualizado:\n" + updated);
        else System.out.println("No fue posible actualizar.");
    }

    private static void deleteGrade() {
        Long id = readLong("Grade ID a eliminar: ");
        if (id == null) { System.out.println("ID invalido."); return; }
        if (gradesService.delete(id)) System.out.println("Calificacion eliminada.");
        else System.out.println("No existe una calificacion con ese ID.");
    }

    // =========================================================
    // ROLES DE USUARIO
    // =========================================================

    private static void showUserRolesMenu() {
        int option;
        do {
            System.out.println("\n===== SUBMENU ROLES DE USUARIO =====");
            System.out.println("1. Asignar rol a usuario");
            System.out.println("2. Listar todas las asignaciones");
            System.out.println("3. Buscar por ID");
            System.out.println("4. Buscar por User ID");
            System.out.println("5. Actualizar asignacion");
            System.out.println("6. Eliminar asignacion");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opcion: ");
            option = readInt();
            switch (option) {
                case 1 -> executeAction(Main::createUserRole);
                case 2 -> executeAction(Main::listUserRoles);
                case 3 -> executeAction(Main::findUserRoleById);
                case 4 -> executeAction(Main::findUserRolesByUserId);
                case 5 -> executeAction(Main::updateUserRole);
                case 6 -> executeAction(Main::deleteUserRole);
                case 0 -> System.out.println("Regresando...");
                default -> System.out.println("Opcion invalida.");
            }
        } while (option != 0);
    }

    private static void createUserRole() {
        System.out.println("\n--- Asignar rol a usuario ---");
        Long userId = readLong("User ID: ");
        Long roleId = readLong("Role ID: ");
        if (userId == null || roleId == null) { System.out.println("IDs invalidos."); return; }
        System.out.println("Asignacion creada:\n" + userRolesService.create(new UserRoles(userId, roleId)));
    }

    private static void listUserRoles() {
        System.out.println("\n--- Lista de roles de usuario ---");
        List<UserRoles> list = userRolesService.findAll();
        if (list.isEmpty()) { System.out.println("No hay asignaciones registradas."); return; }
        list.forEach(System.out::println);
    }

    private static void findUserRoleById() {
        Long id = readLong("UserRole ID: ");
        if (id == null) { System.out.println("ID invalido."); return; }
        System.out.println(userRolesService.findById(id));
    }

    private static void findUserRolesByUserId() {
        Long userId = readLong("User ID: ");
        if (userId == null) { System.out.println("ID invalido."); return; }
        List<UserRoles> list = userRolesService.findByUserId(userId);
        if (list.isEmpty()) { System.out.println("No hay roles para ese usuario."); return; }
        list.forEach(System.out::println);
    }

    private static void updateUserRole() {
        Long id = readLong("UserRole ID a actualizar: ");
        if (id == null) { System.out.println("ID invalido."); return; }
        UserRoles ur = userRolesService.findById(id);
        System.out.println("Presione Enter para conservar el valor actual.");
        String uidStr = readOptionalString("User ID [" + ur.getUserId() + "]: ");
        String ridStr = readOptionalString("Role ID [" + ur.getRoleId() + "]: ");
        Long newUserId = uidStr.isBlank() ? ur.getUserId() : parseLong(uidStr, ur.getUserId());
        Long newRoleId = ridStr.isBlank() ? ur.getRoleId() : parseLong(ridStr, ur.getRoleId());
        UserRoles updated = new UserRoles(newUserId, newRoleId);
        updated.setRolesId(ur.getRolesId());
        if (userRolesService.update(updated)) System.out.println("Actualizado:\n" + updated);
        else System.out.println("No fue posible actualizar.");
    }

    private static void deleteUserRole() {
        Long id = readLong("UserRole ID a eliminar: ");
        if (id == null) { System.out.println("ID invalido."); return; }
        if (userRolesService.delete(id)) System.out.println("Asignacion eliminada.");
        else System.out.println("No existe una asignacion con ese ID.");
    }

    // =========================================================
    // UTILIDADES DE LECTURA
    // =========================================================

    private static void executeAction(Runnable action) {
        try {
            action.run();
        } catch (RuntimeException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private static int readInt() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static Long readLong(String message) {
        System.out.print(message);
        try {
            return Long.parseLong(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static Integer readInteger(String message) {
        System.out.print(message);
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static Double readDouble(String message) {
        System.out.print(message);
        try {
            return Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static Long readOptionalLong(String message) {
        System.out.print(message);
        String value = scanner.nextLine().trim();
        if (value.isBlank()) return null;
        try { return Long.parseLong(value); } catch (NumberFormatException e) { return null; }
    }

    // Devuelve Long.MIN_VALUE si el usuario presionó Enter (sin cambio)
    private static Long readOptionalLongWithCurrent(String message) {
        System.out.print(message);
        String value = scanner.nextLine().trim();
        if (value.isBlank()) return Long.MIN_VALUE;
        try { return Long.parseLong(value); } catch (NumberFormatException e) { return Long.MIN_VALUE; }
    }

    private static String readRequiredString(String message) {
        String value;
        do {
            System.out.print(message);
            value = scanner.nextLine().trim();
        } while (value.isBlank());
        return value;
    }

    private static String readOptionalString(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    // Parseo seguro con fallback
    private static Long parseLong(String value, Long fallback) {
        try { return Long.parseLong(value.trim()); } catch (NumberFormatException e) { return fallback; }
    }

    private static Integer parseInteger(String value, Integer fallback) {
        try { return Integer.parseInt(value.trim()); } catch (NumberFormatException e) { return fallback; }
    }

    private static Double parseDouble(String value, Double fallback) {
        try { return Double.parseDouble(value.trim()); } catch (NumberFormatException e) { return fallback; }
    }
}