package vn.baothai.lecture9_student.repository

import vn.baothai.lecture9_student.data.StudentDao
import vn.baothai.lecture9_student.model.Student
import kotlinx.coroutines.flow.Flow

class StudentRepository(private val studentDao: StudentDao) {

    // Nguồn chân lý duy nhất (Single Source of Truth)
    val allStudents: Flow<List<Student>> = studentDao.getAllStudents()

    suspend fun insert(student: Student) {
        studentDao.insertStudent(student)
    }

    suspend fun delete(student: Student) {
        studentDao.deleteStudent(student)
    }
}
