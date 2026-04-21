import { ReactNode } from "react";
import { useAuth } from "@/typescript/contexts/GlobalContext";
import { useRouter } from "next/navigation";
import { useEffect } from "react";



export default function AuthorizationComponent({ children }: { children: ReactNode }){

    const {user, token, loading} = useAuth();
    const router = useRouter();

    useEffect(() => {
        if(loading === false && !user && !token){
            router.push("/");
        }

    }, [user, token, loading]);

    if(loading === true){
        return(
            <>
                <p>Loading...</p>
            </>
        )
    }

    return children
}