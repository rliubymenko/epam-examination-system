$(document).ready(function () {

    $("#questionId").change(function () {
        const selectedType = $("#questionId option:selected").text().split("|")[0]

        $('#textAnswerDiv').prop("hidden", true);
        $('#numericalAnswerDiv').prop("hidden", true);
        $('#choiceToggler').prop("hidden", true);
        $('#singleChoiceLabel').prop("hidden", true);
        $('#multipleChoiceLabel').prop("hidden", true);
        $('#addAnswerBtn').prop("hidden", true);

        if (selectedType.includes("Text")) {

            resetInputs();
            $('#textAnswerDiv').removeAttr('hidden');
        } else if (selectedType.includes('Numerical')) {

            resetInputs();
            $('#numericalAnswerDiv').removeAttr('hidden');
        } else if (selectedType.includes('Single choice')) {

            resetInputs();
            $('#choiceCheckbox_1')
                .attr('type', 'radio')
                .val(1);
            $('#singleChoiceLabel').removeAttr("hidden");
            $('#choiceToggler').removeAttr('hidden');
            $('#addAnswerBtn').removeAttr('hidden');
        } else if (selectedType.includes('Multiple choice')) {

            resetInputs();
            $('#choiceCheckbox_1')
                .attr('type', 'checkbox')
                .val(1);
            $('#multipleChoiceLabel').removeAttr("hidden");
            $('#choiceToggler').removeAttr('hidden');
            $('#addAnswerBtn').removeAttr('hidden');
        } else {

            resetInputs();
        }

    });

    cloneAndModifyInputGroupFields()

    $("body").on("click", ".removeNodeBtnField", function (e) {
        const selectedType = $("#questionId option:selected").text().split("|")[0];
        const closestCheckbox = $(e.target).closest(".checkboxChoice").find('.form-check-input');
        if (selectedType.includes('Single choice')) {
            if (closestCheckbox.is(":checked")) {
                $(e.target)
                    .closest("#choiceToggler")
                    .find("#choiceCheckbox_1")
                    .prop('checked', true);
            }
        }
        $(e.target).closest(".checkboxChoice").remove();
    });
});

function resetInputs() {
    $("input[name='answers']").val("");
    $("input[name='answers.choice']").val("");
    $("textarea[name='answers']").val("");
    $("textarea[name='answers.answer']").val("");
    document.querySelectorAll(".checkboxChoice").forEach((element, index) => {
        if (index > 0) {
            element.remove()
        }
    });
}

// Clone input group fields
function cloneAndModifyInputGroupFields() {

    $("body").on("click", ".copyNodeBtnField", function (e) {
        const index = $(e.target).closest("#choiceToggler")
            .find(".checkboxChoice").length + 1;

        // Clone input row
        const clonedElement = $(e.target).closest(".checkboxChoice")
            .clone(true);

        // Append data and remove disabled state from remove button
        $(e.target).closest("#choiceToggler")
            .last()
            .append(clonedElement)
            .find(".removeNodeBtnField:not(:first)")
            .prop("disabled", false);

        $(e.target).closest("#choiceToggler")
            .find(".removeNodeBtnField")
            .first()
            .prop("disabled", true);

        // Change textarea id
        $(e.target)
            .closest("#choiceToggler")
            .find(".checkboxChoice")
            .last()
            .find("textarea")
            .attr("id", "choiceTextArea_" + index);

        $(e.target)
            .closest("#choiceToggler")
            .find(".checkboxChoice")
            .last()
            .find("input")
            .attr("id", "choiceCheckbox_" + index)
            .prop("value", index);
    });
}


// Append data method by submission
$(document).ready(function () {

    $("#addAnswerBtn").click(function () {
        const index = $("#choiceToggler").find(".checkboxChoice").length + 1;

        const inputType = $("#questionId option:selected").text().split("|")[0]

        $("#choiceToggler").append(`<div class="btn-group-justified checkboxChoice mt-4">
                                            <div class="input-group">
                                                <div class="input-group-text">
                                                    <input id="choiceCheckbox_${index}"
                                                           class="form-check-input"
                                                           name="answers.choice"
                                                           type="${inputType.includes('Single choice') ? 'radio' : 'checkbox'}"
                                                           value="${index}"
                                                           aria-label="Button for following text input"/>
                                                </div>
                                                <textarea id="choiceTextArea_${index}"
                                                          rows="4"
                                                          name="answers.answer"
                                                          class="form-control form-control-lg"
                                                          aria-label="Single choice field"
                                                ></textarea>
                                                <div class="input-group-text">
                                                    <button type="button"
                                                            class="copyNodeBtnField btn btn-success">
                                                        <i class="far fa-copy"></i>
                                                    </button>

                                                    <button type="button"
                                                            class="removeNodeBtnField btn btn-danger"
                                                            disabled>
                                                        <i class="far fa-trash-alt"></i>
                                                    </button>
                                                </div>
                                            </div>
                                        </div>`);

        $("#choiceToggler")
            .find(".removeNodeBtnField:not(:first)")
            .prop("disabled", false);

        $("#choiceToggler").find(".removeNodeBtnField")
            .first()
            .prop("disabled", true);
    });

});
