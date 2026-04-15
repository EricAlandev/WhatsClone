'use client'

import { UserType } from "@/typescript/types/UserType";
import Link from "next/link";
import { useState } from "react";


type RegisterForm = {
    send : (data: UserType) => void;
}

export default function RegisterForm({
    send
} : RegisterForm){

    const [data, setData] = useState<UserType>({
        name: "",
        birthday: "",
        city: "",
        country: "",
        ddd: "",
        number: "",
        password: "",
        email: ""

    });

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setData(prev => ({
            ...prev,
            [name]: value
        }));
    };

    return(
        <div
            className="w-[80vw] mx-auto mt-5"
        >
            <h1
                className="mt-5 mb-2 text-[18px] underline"
            >
                Register
            </h1>

            <form
                className="flex flex-col gap-4"
                onSubmit={(e) => {
                    e.preventDefault();
                    send(data);
                }}
            >
                {/* Core data of user */}
                <fieldset className="flex flex-col">
                    <label htmlFor="name">Name</label>
                    <input
                        id="name"
                        name="name"
                        value={data.name}
                        onChange={handleChange}
                        className="bg-[#A0A0A0] rounded-md px-2"
                    />

                    <label htmlFor="email">Email</label>
                    <input
                        id="email"
                        name="email"
                        value={data.email}
                        onChange={handleChange}
                        className="bg-[#A0A0A0] rounded-md px-2"
                    />


                    <label htmlFor="password">Password</label>
                    <input
                        id="password"
                        name="password"
                        type="password"
                        value={data.password}
                        onChange={handleChange}
                        className="bg-[#A0A0A0] rounded-md px-2"
                    />

                    <label htmlFor="birthday">Birthday</label>
                    <input
                        id="birthday"
                        name="birthday"
                        type="date"
                        value={data.birthday}
                        onChange={handleChange}
                        className="bg-[#A0A0A0] rounded-md px-2"
                    />
                </fieldset>

                {/* City data of user */}
                <fieldset className="flex flex-col">
                    <label htmlFor="city">City</label>
                    <input
                        id="city"
                        name="city"
                        value={data.city}
                        onChange={handleChange}
                        className="bg-[#A0A0A0] rounded-md px-2"
                    />

                    <label htmlFor="country">Country</label>
                    <input
                        id="country"
                        name="country"
                        value={data.country}
                        onChange={handleChange}
                        className="bg-[#A0A0A0] rounded-md px-2"
                    />
                </fieldset>

                {/* Number data of user */}
                <fieldset className="flex flex-col">
                    <label htmlFor="ddd">DDD</label>
                    <input
                        id="ddd"
                        name="ddd"
                        value={data.ddd}
                        onChange={handleChange}
                        className="bg-[#A0A0A0] rounded-md px-2"
                        maxLength={2}
                        minLength={2}
                    />

                    <label htmlFor="number">Cellphone Number</label>
                    <input
                        id="number"
                        name="number"
                        value={data.number}
                        onChange={handleChange}
                        className="bg-[#A0A0A0] rounded-md px-2"
                        maxLength={9}
                        minLength={9}
                    />
                </fieldset>

                <button
                    type="submit"
                    className="mt-5 p-2 bg-[black] text-[white] rounded-md hover:bg-gray-800 transition-colors"
                >
                    Register
                </button>
            </form>

            {/*Link to login */}
            <div
                className="flex gap-2"
            >
                <p>Dosn't have a account? </p>

                <Link
                href={"/"}
                className="underline"
                >
                    Register
                </Link>
            </div>
        </div>
    )
}