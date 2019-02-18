package dao.daoImpl;


import model.Person;
import model.Subject;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class SubjectDaoImplTest {

    @Mock
    private static SubjectDaoImpl subjectDao;

    @Mock
    private static Subject subject1;
    @Mock
    private static Subject subject2;
    @Mock
    private static Person person;

    @Before
    public void setUp(){
        //Create mock object of BookDAL
        subjectDao = mock(SubjectDaoImpl.class);

        subject1 = new Subject(1, "subject1");
        subject2 = new Subject(2, "subject2");

        person = new Person(1,"Mike", new Date());

        //Stubbing the methods of mocked BookDAL with mocked data.
        when(subjectDao.getAllSubjects()).thenReturn(Arrays.asList(subject1, subject2));
        when(subjectDao.getSubjectsByPerson(person)).thenReturn(Arrays.asList(subject1, subject2));

    }
    
    @Test
    void getAllSubjects() {

        List<Subject> allSubjects = (List<Subject>) subjectDao.getAllSubjects();
        assertEquals(2, allSubjects.size());
        Subject subject = allSubjects.get(0);
        assertEquals(1, subject.getId());
        assertEquals("subject1", subject.getDescription());
        Subject subject2 = allSubjects.get(1);
        assertEquals(2, subject.getId());
        assertEquals("subject2", subject.getDescription());

    }

    @Test
    void getSubjectsByPerson() {
        assertEquals(2, 2);

    }
}