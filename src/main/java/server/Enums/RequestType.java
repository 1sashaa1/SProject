package server.Enums;

public enum RequestType { //перечисление, набор констант
    REGISTER, //объект
    LOGIN,

    ADDDEPOSIT,
    GETDEPOSITS,
    DELETEDEPOSIT,
    UPDATEDEPOSIT,

    GETCLIENT,
    GETCLIENTS,

    SENDNOTIFICATION,
    GETNOTIFICATIONS,

    OPENDEPOSIT,
    CLOSEDEPOSIT,
    PROLONGDEPOSIT,
    GETEXTRACT,

    EOPENDEPOSIT,
    ECLOSEDEPOSIT,
    EPROLONGDEPOSIT,

    GETCLIENTSDEPOSITS,
    GETMYDEPOSITS,

    ASKQUESTION,
    UNSWERQUESTION,
    GETQUESTIONS,
    GETALLQUESTIONS,

    UPDATE_NOTIFICATIONS,

    DELETECLIENT,
    UPDATECLIENT,

    DELETEEMPLOYEE,
    UPDATEEMPLOYEE,
    CREATEEMPLOYEE,

    GETTRUECLIENTS,
    GETTRUEEMPLOYEES,

    GETOPERATIONS
}