

type SkeListValue = {
    type: "list" | "chat",

    //list type
    idList?: number,
    nameList?: string,
    description?: string,
    image_url?: string,
    code?: number,

}

export default function SkeListValue({
    type,
    idList,
    image_url,
    nameList,
    description

} : SkeListValue){

    //define what gonna renderize
    let typeValue : number;

    switch(type){
        case"list":
            typeValue = 1;
        case"chat":
            typeValue = 2;
        default:
            typeValue = 2;
    }

    return(
        <>
            {typeValue === 1 && (
                <>
                    <img
                        src={image_url}
                    />
                </>
            )}
        </>
    )
}