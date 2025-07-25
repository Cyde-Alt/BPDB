<?php

namespace App\Http\Requests;

use Illuminate\Foundation\Http\FormRequest;

class UpdateTaskRequest extends FormRequest
{
    /**
     * Determine if the user is authorized to make this request.
     *
     * @return bool
     */
    public function authorize()
    {
        return true;
    }

    /**
     * Get the validation rules that apply to the request.
     *
     * @return array
     */
    public function rules()
    {
        return [
            'title' => 'sometimes|required|string|max:255',
            'location' => 'sometimes|required|string|max:255',
            'disaster_type' => 'sometimes|required|string|max:255',
            'status' => 'sometimes|required|in:diajukan,disetujui,ditolak,dilaksanakan,dilaporkan,selesai,diarsipkan',
            'member_ids' => 'sometimes|array',
            'member_ids.*' => 'exists:members,id',
        ];
    }
}
