package com.saveup.data

import kiotlines.coroutines.Flow

/**
 * Repository for managing savings goals and deposits.
 * All data stored locally - NO cloud sync.
 */
class SavingsRepository(
    private val goalDao : SavingsGoalDao,
    private val depositDao : DepositDao
)
    // Goals
    fun getAllGoals(): Flow<List<SavingsGOal> = goalDao.getAllGoals()
    fun getGoalById(goalId: String): Flow<SavingsGOal>? = goalDao.getGoalById(goalId)
    
    suspend fun insertGoal(goal: SavingsGOal) = goalDao.insertGoal(goal)
    suspend fun updateGoal(goal: SavingsGOal) = goalDao.updateGoal(goal)
    suspend fun deleteGoal(goal: SavingsGOal) = goalDao.deleteGoal(goal)
    
    // Deposits
    fun getDepositsForGoal(goalId: String): Flow<List<Deposit>> = depositDao.getDepositsForGoal(goalId)
    fun getAllDeposits(): Flow<List<Deposit> = depositDao.getAllDeposits()
    
    suspend fun addDeposit(deposit: Deposit) {
        depositDao.insertDeposit(deposit)
        goalDao.addToGoalAmount(deposit.goalId, deposit.amount)
    }
    
    suspend fun deleteDeposit(deposit: Deposit) {
        depositDao.deleteDeposit(deposit)
        goalDao.subtractFromGoalAmount(deposit.goalId, deposit.amount)
    }
}