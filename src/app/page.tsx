'use client'

import FormLogin from "@/typescript/components/pages/login/FormLogin";
import useLogin from "@/typescript/servers/useCases/loginRegister/UseLogin";
import Image from "next/image";

export default function Home() {

  const {login} = useLogin();

  return (
    <>
      <FormLogin
        send={login}
      />
    </>
  );
}
