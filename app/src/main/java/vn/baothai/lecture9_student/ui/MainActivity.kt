package vn.baothai.lecture9_student.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import vn.baothai.lecture9_student.R
import vn.baothai.lecture9_student.data.AppDatabase
import vn.baothai.lecture9_student.repository.StudentRepository
import vn.baothai.lecture9_student.viewmodel.StudentViewModel
import vn.baothai.lecture9_student.viewmodel.StudentViewModelFactory

class MainActivity : AppCompatActivity() {

    // Khởi tạo Database Singleton và Repository ở tầng Host
    private val database by lazy { AppDatabase.getDatabase(this) }
    private val repository by lazy { StudentRepository(database.studentDao()) }

    private val viewModel: StudentViewModel by viewModels {
        StudentViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // MainActivity chỉ chứa FragmentContainerView nạp StudentListFragment
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, StudentListFragment())
                .commit()
        }
    }
}
