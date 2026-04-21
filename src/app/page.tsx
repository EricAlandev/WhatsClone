'use client'

import FormLogin from "@/typescript/components/pages/login/FormLogin";
import useLogin from "@/typescript/servers/useCases/loginRegister/UseLogin";

export default function Home() {

  const {uselogin} = useLogin();

  return (
    <>
      <FormLogin
        send={uselogin}
      />
    </>
  );
}
