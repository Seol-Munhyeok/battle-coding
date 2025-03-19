import { useState, useEffect, useCallback } from 'react';
import { useNavigate } from 'react-router-dom';
import InputInfo from './InputInfo';
import './UserInputForm.scss';

const UserInputForm = () => {
    const [info, setInfo] = useState({
        nickname: '',
        email: '',
        password: '',
        isValidNickname: false,
        isValidPwd: false,
        isValidPwdChk: false,
    });

    const [isValidButton, setIsValidButton] = useState(false);
    const navigate = useNavigate();

    const infoHandler = (key, value) => {
        setInfo(prevState => ({
                ...prevState,
                [key]: value 
        }));
    };

    const validationHandler = useCallback((key, isValid) => {
        if (!["isValidNickname", "isValidPwd", "isValidPwdChk"].includes(key)) {
            console.error(`Invalid key: ${key}`);
            return;
        }    
        
        setInfo(prevState => ({
            ...prevState,
            [key]: isValid,
        }));
        console.log(`Validation Updated: ${key} → ${isValid}`);
    }, []);

    const submitHandler = event => {
        event.preventDefault();
        console.log('Submitting form:', info);
    }

    useEffect(() => {
        setIsValidButton(info.isValidNickname && info.isValidPwd && info.isValidPwdChk);
    }, [info]);

    useEffect(() => {
        console.log("isValidButton 상태:", isValidButton);
    }, [isValidButton]);

    return (
        <form className="regsiterSubmitForm" onSubmit={submitHandler}>
            <InputInfo onChange={infoHandler} OnChangeValidation={validationHandler} />
            <button disabled={!isValidButton}  onClick={() => console.log("클릭됨")}>회원가입</button>
        </form>
    );
};

export default UserInputForm;

