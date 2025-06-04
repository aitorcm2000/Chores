package com.aitor.chores.communication.users_groups

import com.aitor.chores.model.users_groups.UserGroupChoresObject
import com.aitor.chores.model.users_groups.UserGroupChoresObjectResponse
import com.aitor.chores.model.users_groups.UserGroupMemberObject
import com.aitor.chores.model.users_groups.UserGroupObject
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.tasks.await

class UsersGroupQueries (val collection: CollectionReference){

    suspend fun getGroupById (id: String){
        val snap = collection.document(id).get().await()

        if (snap.exists() && snap.data != null) {
            val id = snap.id.toString()
            val name = snap.get(UsersGroupReferenceNames.GROUPNAME).toString()
            val members = snap.get(UsersGroupReferenceNames.MEMBERS) as List<UserGroupMemberObject>
            val choresCompletedResponse = snap.get(UsersGroupReferenceNames.CHORESCOMPLETED) as List<UserGroupChoresObjectResponse>




//            val group = UserGroupObject(
//                id,
//                name,
//                members,
//
//            )
//            println(group)
        }
    }

//    private fun completedChores (chores : List<UserGroupChoresObjectResponse>)
//    : List<UserGroupChoresObject>{
//
//        for (chore in chores){
//            val ref = chore.chore.get().await()
//
//            if ( ref.exists() && ref.data != null ){
//                val choreObject = UserGroupChoresObject(
//                    ,
//                )
//            }
//
//        }
//    }
}