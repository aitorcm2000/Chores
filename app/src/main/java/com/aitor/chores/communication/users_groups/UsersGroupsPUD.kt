package com.aitor.chores.communication.users_groups

import com.aitor.chores.communication.TableReferenceNames
import com.aitor.chores.model.users_groups.UserGroupInputObject
import com.aitor.chores.model.users_groups.UserGroupOutputObject
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UsersGroupsPUD(db: FirebaseFirestore) {

    private val usersGroupTable : CollectionReference =
        db.collection(TableReferenceNames.USER_GROUP)

    suspend fun addUsersGroup (usersGroup : UserGroupOutputObject){

        val ref = usersGroupTable.add(usersGroup).await()
    }

    suspend fun updateUsersGroup ( userGroup : UserGroupInputObject) {

        val usergroupOutput = UserGroupOutputObject(
            userGroup.groupName, userGroup.members, userGroup.choresCompleted
        )

        val ref = usersGroupTable.document(userGroup.id).set(usergroupOutput).await()
    }
}