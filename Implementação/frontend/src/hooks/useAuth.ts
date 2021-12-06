import { useContext } from 'react';
import { AuthCompanyContext } from '../context/AuthCompanyContextProvider';
import { AuthStudentContext } from '../context/AuthStudentContextProvider';
import { AuthTeacherContext } from '../context/AuthTeacherContextProvider';
import { AuthUniversityContext } from '../context/AuthUniversityContextProvider';

export function useAuthCompany() {
    const value = useContext(AuthCompanyContext)

    return value;
}

export function useAuthUniversity() {
    const value = useContext(AuthUniversityContext)

    return value;
}

export function useAuthTeacher() {
    const value = useContext(AuthTeacherContext)

    return value;
}

export function useAuthStudent() {
    const value = useContext(AuthStudentContext)

    return value;
}