

type DeletePopuP = {
    open: boolean;
    setIsModalDelete: any;
    deleteMessage: () => void;
}

export default function DeletePopuP({open, setIsModalDelete, deleteMessage} : DeletePopuP){

    if(open === false) return null;

    return(
        <>
            <div 
                onClick={() => {
                    setIsModalDelete(false);
                }}
                className="fixed inset-0 bg-[black] opacity-70"
            ></div>

            <div className="fixed top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 w-[80vw] h-[30vh] bg-[white] rounded-md">
                
                {/*Close buttom */}
                <img
                    src={"/messages/close.png"}
                    onClick={() => {
                        setIsModalDelete(false);
                    }}
                    className="absolute top-3 right-3 max-w-[25px] max-h-[25px]"
                />

                {/*Body popUp */}
                <div
                    className="absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2"
                >
                    <h3 className="text-center">Excluir mensagens?</h3>

                    <div
                        className="flex justify-center gap-2 mt-4.5"
                    >
                        <button
                            onClick={() => {
                                deleteMessage();
                            }}
                            className="w-[100px] p-2 text-[#F1F1F1] bg-[green] rounded-md"
                        >
                            Sim
                        </button>

                        <button
                             onClick={() => {
                                setIsModalDelete(false);
                            }}
                            className="w-[100px] p-2 text-[#F1F1F1] bg-[red] rounded-md"
                        >
                            Não
                        </button>
                    </div>
                </div>
                

            
            </div>
        </>
    )
}