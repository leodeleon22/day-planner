package com.leodeleon.planner.features.edit.rules

import com.leodeleon.data.entities.EmployeeDetails
import com.leodeleon.data.entities.Gender
import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class EditActionsTest : FunSpec() {

    private val employee = EmployeeDetails(1, "Test", "Test", Gender.Male)

    init {

        test("Can update employee given updated employee data") {
            forAll(
                row(1, "Test", "Test", Gender.Male, false),
                row(2, "Test", "Test", Gender.Male, false),
                row(1, "Text", "Test", Gender.Male, true),
                row(1, "Test", "Text", Gender.Male, true),
                row(1, "Test", "Text", Gender.Male, true),
                row(1, "Test", "Test", Gender.Female, true)
            ) { id, first, last, gender, expected ->
                val updated = EmployeeDetails(id, first, last, gender)
                val subject = EditActions(employee)

                subject.canUpdate(updated) shouldBe expected
            }
        }
    }
}
