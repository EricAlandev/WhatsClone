'use client'

import { UserType } from "@/typescript/types/UserType";
import Link from "next/link";
import { useState } from "react";

type LoginForm = {
    send: (data: UserType) => void;
}

export default function FormLogin({ send }: LoginForm) {

    const [data, setData] = useState<UserType>({
        email: "",
        password: ""
    });

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setData(prev => ({
            ...prev,
            [name]: value
        }));
    };

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        send(data);
    };

    return (
        <div className="w-[80vw] mx-auto mt-5">
            <h1 className="mt-5 mb-2 text-[18px] underline">
                Login
            </h1>

            <form 
                onSubmit={handleSubmit}
                className="flex flex-col"
            >
                {/* Name Input */}
                <label htmlFor="email">Email</label>
                <input
                    id="email"
                    name="email"
                    type="text"
                    value={data.email}
                    onChange={handleChange}
                    className="bg-[#A0A0A0] rounded-md p-1"
                />

                {/* Password Input */}
                <label htmlFor="password">Senha</label>
                <input
                    id="password"
                    name="password"
                    type="password"
                    value={data.password}
                    onChange={handleChange}
                    className="bg-[#A0A0A0] rounded-md p-1"
                />

                <button
                    type="submit"
                    className="mt-5 p-2 bg-[black] text-[white] rounded-md hover:opacity-90"
                >
                    Logar
                </button>
            </form>

            <div className="flex gap-2 mt-4">
                <p>Don't have an account?</p>
                <Link
                    href={"/register"}
                    className="underline"
                >
                    Register
                </Link>
            </div>
        </div>
    );
}