import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { AuthTeacherContextProvider } from './context/AuthTeacherContextProvider';
import { AuthCompanyContextProvider } from './context/AuthCompanyContextProvider';
import { AuthStudentContextProvider } from './context/AuthStudentContextProvider';
import { AuthUniversityContextProvider } from './context/AuthUniversityContextProvider';
import { Home } from './pages/Home/Home';
import { LoginCompany } from './pages/LoginCompany/LoginCompany';
import { LoginStudent } from './pages/LoginStudent/LoginStudent';
import { LoginTeacher } from './pages/LoginTeacher/LoginTeacher';
import { LoginUniversity } from './pages/LoginUniveristy/LoginUniveristy';
import { SubscribeCompany } from './pages/SubscribeCompany/SubscribeCompany';
import { SubscribeStudent } from './pages/SubscribeStudent/SubscribeStudent';
import { SubscribeTeacher } from './pages/SubscribeTeacher/SubscribeTeacher';
import { SubscribeUniversity } from './pages/SubscribeUniversity/SubscribeUniversity';
import './styles/global.scss'
import { CompanyHome } from './pages/CompanyHome/CompanyHome';
import { StudentHome } from './pages/StudentHome/StudentHome';
import { TeacherHome } from './pages/TeacherHome/TeacherHome';
import { UniversityHome } from './pages/UniversityHome/UniversityHome';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/subscribe/company" element={<SubscribeCompany />} />
        <Route path="/subscribe/student" element={<SubscribeStudent />} />
        <Route path="/subscribe/teacher" element={<SubscribeTeacher />} />
        <Route path="/subscribe/university" element={<SubscribeUniversity />} />
      </Routes>
      <AuthCompanyContextProvider>
        <Routes>
          <Route path="/company" element={<LoginCompany />} />
          <Route path="/company/home" element={<CompanyHome />} />
        </Routes>
      </AuthCompanyContextProvider>
      <AuthStudentContextProvider>
        <Routes>
          <Route path="/student" element={<LoginStudent />} />
          <Route path="/student/home" element={<StudentHome />} />
        </Routes>
      </AuthStudentContextProvider>
      <AuthTeacherContextProvider>
        <Routes>
          <Route path="/teacher" element={<LoginTeacher />} />
          <Route path="/teacher/home" element={<TeacherHome />} />
        </Routes>
      </AuthTeacherContextProvider>
      <AuthUniversityContextProvider>
        <Routes>
          <Route path="/university" element={<LoginUniversity />} />
          <Route path="/university/home" element={<UniversityHome />} />
        </Routes>
      </AuthUniversityContextProvider>

    </BrowserRouter >
  );
}

export default App;
